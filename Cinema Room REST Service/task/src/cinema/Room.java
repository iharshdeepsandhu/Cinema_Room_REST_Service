package cinema;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


public class Room {
    private int total_rows;
    private int total_columns;
    ArrayList<Seats> available_seats = new ArrayList<>(81);
    @JsonIgnore
    List<OrderedSeat> ordered_seats;



    public Room(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.ordered_seats = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++ ) {
                Seats s = new Seats(i, j);
                available_seats.add(s);
            }
        }


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

    public ArrayList<Seats> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(ArrayList<Seats> available_seats) {
        this.available_seats = available_seats;
    }

    public List<OrderedSeat> getOrdered_seats() {
        return ordered_seats;
    }

    public void setOrdered_seats(List<OrderedSeat> ordered_seats) {
        this.ordered_seats = ordered_seats;
    }
}
