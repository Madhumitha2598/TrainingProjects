package day6;

import java.util.*;

class TicketBookingSystem {
    private Queue<Integer> availableSeats;
 
    public TicketBookingSystem(int totalSeats) {
        availableSeats = new LinkedList<>();
        for (int i = 1; i <= totalSeats; i++) {
            availableSeats.add(i);
        }
    }
 
    // synchronized to prevent race condition
    public synchronized List<Integer> bookTickets(int requestedSeats) {
        List<Integer> booked = new ArrayList<>();
 
        // allocate as many seats as available
        for (int i = 0; i < requestedSeats && !availableSeats.isEmpty(); i++) {
            booked.add(availableSeats.poll());
        }
        return booked;
    }
 
    public synchronized int remainingSeats() {
        return availableSeats.size();
    }
}
 
class User extends Thread {
    private TicketBookingSystem system;
    private int userId;
 
    public User(TicketBookingSystem system, int userId) {
        this.system = system;
        this.userId = userId;
    }
 
    @Override
    public void run() {
        Random rand = new Random();
        int requested = rand.nextInt(5) + 1; // 1 to 5 seats
        List<Integer> booked = system.bookTickets(requested);
 
        if (booked.size() == requested) {
            System.out.println("User-" + userId + " requested " + requested +
                    " seats → Booking successful (Seats: " + booked + ")");
        } else if (booked.isEmpty()) {
            System.out.println("User-" + userId + " requested " + requested +
                    " seats → Booking failed (No seats available)");
        } else {
            System.out.println("User-" + userId + " requested " + requested +
                    " seats → Only " + booked.size() + " seat(s) available → Booking partially successful (Seats: " + booked + ")");
        }
    }
}
 
public class MultiThreadedTicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(50);
 
        // create 10 users
        Thread[] users = new Thread[10];
        for (int i = 0; i < 10; i++) {
            users[i] = new User(system, i + 1);
            users[i].start();
        }
 
        // wait for all users to finish
        for (int i = 0; i < 10; i++) {
            try {
                users[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
 
        System.out.println("\nAll tickets processed. Remaining seats = " + system.remainingSeats());
    }
}
