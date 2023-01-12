package cinema;
//entity class
public class Seats {
    private int row;
    private int column;
    private int price;
    public Seats() {}

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
        if (row > 4) {
            price = 8;
        }
        else {
            price = 10;
        }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seats seat = (Seats) o;

        if (row != seat.row) return false;
        return column == seat.column;
    }

}
