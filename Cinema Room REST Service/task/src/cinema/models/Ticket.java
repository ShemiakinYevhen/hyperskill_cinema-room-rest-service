package cinema;

import cinema.models.Seat;

import java.util.Objects;
import java.util.UUID;

public class Ticket {

    private UUID token;
    private Seat ticket;

    public Ticket() {}

    public Ticket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket that = (Ticket) o;
        return Objects.equals(token, that.token) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, ticket);
    }

    @Override
    public String toString() {
        return "{\"token\": " + token
                + ", \"ticket\": " + ticket
                + "}";
    }
}
