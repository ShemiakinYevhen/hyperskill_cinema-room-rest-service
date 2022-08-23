package cinema.models;

import cinema.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CinemaRoom {

    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats;

    public CinemaRoom() {
        total_rows = 9;
        total_columns = 9;
        available_seats = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                available_seats.add(new Seat(i, j, i <= 4 ? 10 : 8));
            }
        }
    }

    public CinemaRoom(int totalRowsCount, int totalColumnsCount) {
        this.total_rows = totalRowsCount;
        this.total_columns = totalColumnsCount;
        available_seats = new ArrayList<>();

        for (int i = 1; i <= totalRowsCount; i++) {
            for (int j = 1; j <= totalColumnsCount; j++) {
                available_seats.add(new Seat(i, j, i <= 4 ? 10 : 8));
            }
        }
    }

    public CinemaRoom(int totalRowsCount, int totalColumnsCount, List<Seat> availableSeats) {
        this.total_rows = totalRowsCount;
        this.total_columns = totalColumnsCount;
        this.available_seats = availableSeats;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaRoom that = (CinemaRoom) o;
        return total_rows == that.total_rows && total_columns == that.total_columns && Objects.equals(available_seats, that.available_seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total_rows, total_columns, available_seats);
    }

    @Override
    public String toString() {
        return "CinemaRoom{" +
                "totalRowsCount=" + total_rows +
                ", totalColumnsCount=" + total_columns +
                ", availableSeats=" + available_seats +
                '}';
    }
}
