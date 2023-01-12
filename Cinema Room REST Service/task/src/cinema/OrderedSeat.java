package cinema;



import java.util.UUID;

public class OrderedSeat {
    private UUID token;
    private Seats ticket;
    public OrderedSeat () {}
    public OrderedSeat(UUID token, Seats ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seats getTicket() {
        return ticket;
    }

    public void setTicket(Seats ticket) {
        this.ticket = ticket;
    }
}
