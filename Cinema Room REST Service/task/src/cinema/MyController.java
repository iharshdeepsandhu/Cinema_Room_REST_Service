package cinema;

import org.apache.el.parser.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class MyController {

    static class  Token {
        UUID token;

        public Token() {
        }

        public Token(UUID token) {
            this.token = token;
        }

        public UUID getToken() {
            return token;
        }

        public void setToken(UUID token) {
            this.token = token;
        }
    }

    Room room = new Room(9,9);




    //get the seats
    @GetMapping("/seats")
    public Room getSeats() {

        return room;
    }
    @PostMapping("/purchase")
    public ResponseEntity<?> postPurchase(@RequestBody Seats seat) {
        if ((seat.getColumn() > room.getTotal_columns())
                || (seat.getRow() > room.getTotal_rows())
                || (seat.getRow() < 1)
                || (seat.getColumn() < 1)) {
            return new ResponseEntity<>(Map.of("error","The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }

        for (int i = 0; i < room.getAvailable_seats().size(); i++) {
            Seats s = new Seats(seat.getRow(), seat.getColumn());
            if (room.getAvailable_seats().get(i).equals(s)) {
                OrderedSeat orderedSeat = new OrderedSeat(UUID.randomUUID(),s);
                room.getOrdered_seats().add(orderedSeat);
                room.getAvailable_seats().remove(seat);
                return new ResponseEntity<>(orderedSeat, HttpStatus.OK);

            }
            else if(i == room.getAvailable_seats().size() - 1 && !room.getAvailable_seats().get(i).equals(s)) {
                return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
            }


        }
        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {
        List<OrderedSeat> orderedseats = room.getOrdered_seats();
        for (OrderedSeat orderedSeat : orderedseats) {
            if (orderedSeat.getToken().equals(token.getToken())) {
                orderedseats.remove(orderedSeat);
                room.getAvailable_seats().add(orderedSeat.getTicket());
                return new ResponseEntity<>(Map.of("returned_ticket", orderedSeat.getTicket()), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> stats (@RequestParam(required = false) String password) {
        if(password != null && password.equals("super_secret")) {
            Map <String, Integer> statistic = new HashMap<>();
            int currentIncome = 0;
            for (OrderedSeat orderedSeat : room.getOrdered_seats()) {
                currentIncome += orderedSeat.getTicket().getPrice();
            }
            int numberOfAvailableSeats = room.getAvailable_seats().size();
            int numberOfPurchasedTickets = room.getOrdered_seats().size();
            statistic.put("current_income", currentIncome);
            statistic.put("number_of_available_seats", numberOfAvailableSeats);
            statistic.put("number_of_purchased_tickets", numberOfPurchasedTickets);
            return new ResponseEntity<>(statistic, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.valueOf(401));
        }
    }

}
