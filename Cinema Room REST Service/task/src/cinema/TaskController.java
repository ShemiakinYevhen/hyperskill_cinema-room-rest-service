package cinema;

import cinema.models.CinemaRoom;
import cinema.models.Seat;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private final CinemaRoom cinemaRoom = new CinemaRoom();
    private final List<Ticket> reservedSeats = new ArrayList<>();
    private static final Gson gson = new Gson();
    private static final String SUPER_USER_PASSWORD = "super_secret";

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoomInfo() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<JsonObject> buyATicket(@RequestBody Seat seatToBook) {
        JsonObject response = new JsonObject();

        if (seatToBook.getRow() > cinemaRoom.getTotal_rows()
                || seatToBook.getColumn() > cinemaRoom.getTotal_columns()
                || seatToBook.getRow() < 1
                || seatToBook.getColumn() < 1) {
            response.addProperty("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        seatToBook.setPrice(seatToBook.getRow() <= 4 ? 10 : 8);

        if (!cinemaRoom.getAvailable_seats().contains(seatToBook)) {
            response.addProperty("error", "The ticket has been already purchased!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        cinemaRoom.getAvailable_seats().remove(seatToBook);
        Ticket ticket = new Ticket(UUID.randomUUID(), seatToBook);

        reservedSeats.add(ticket);

        return new ResponseEntity<>(gson.toJsonTree(ticket).getAsJsonObject(), HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<JsonObject> returnATicket(@RequestBody Ticket ticket) {
        JsonObject response = new JsonObject();

        if (reservedSeats.stream().noneMatch(tempTicket -> tempTicket.getToken().equals(ticket.getToken()))) {
            response.addProperty("error", "Wrong token!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Ticket ticketToReturn = reservedSeats.stream()
                .filter(tempTicket -> tempTicket.getToken().equals(ticket.getToken())).findFirst().orElse(new Ticket());

        reservedSeats.remove(ticketToReturn);

        cinemaRoom.getAvailable_seats().add(ticketToReturn.getTicket());
        cinemaRoom.setAvailable_seats(cinemaRoom.getAvailable_seats().stream()
                .sorted((firstSeat, secondSeat) -> Integer.compare(firstSeat.getRow(), secondSeat.getRow())
                        + Integer.compare(firstSeat.getColumn(), secondSeat.getColumn()))
                .collect(Collectors.toList()));

        response.add("returned_ticket", gson.toJsonTree(ticketToReturn.getTicket()).getAsJsonObject());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<JsonObject> getStatistics(@RequestParam(required = false) String password) {
        JsonObject response = new JsonObject();

        if (!Objects.equals(password, SUPER_USER_PASSWORD)) {
            response.addProperty("error", "The password is wrong!");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        response.addProperty("current_income", reservedSeats.stream()
                .mapToInt(ticket -> ticket.getTicket().getPrice())
                .sum());
        response.addProperty("number_of_available_seats", cinemaRoom.getAvailable_seats().size());
        response.addProperty("number_of_purchased_tickets", reservedSeats.size());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
