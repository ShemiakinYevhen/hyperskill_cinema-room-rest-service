package cinema.models;

import java.util.Objects;

public class Seat {

    private int row;
    private int column;
    private int price;

    public Seat() {
        row = 0;
        column = 0;
        price = 0;
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        price = 0;
    }

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "{\"row\": " + row
                + ", \"column\": " + column
                + ", \"price\": " + price
                + "}";
    }
}
