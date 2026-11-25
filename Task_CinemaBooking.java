
import java.util.*;

class Task_CinemaBooking {

    static class Seat {
        final char row;
        final int number;

        Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        @Override
        public String toString() {
            return "" + row + number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Seat seat = (Seat) o;
            return row == seat.row && number == seat.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }
    }

    public static void main(String[] args) {

        HashMap<Seat, String> bookings = new HashMap<>();
        HashSet<Seat> broken = new HashSet<>();

        // Part A
        bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");

        System.out.println("Bookings:           " + bookings);

        // Part B
        System.out.println("Who booked C3:      " +
                bookings.get(new Seat('C', 3)));

        // Part C
        System.out.println("Is A2 taken:        " +
                bookings.containsKey(new Seat('A', 2)));

        System.out.println("Is E6 taken:        " +
                bookings.containsKey(new Seat('E', 6)));

        // Part D
        String result = bookings.putIfAbsent(
                new Seat('B', 5), "Youssef");

        if (result == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by " + result);
        }

        // Part E
        bookings.remove(new Seat('A', 1));
        bookings.put(new Seat('D', 2), "Ali");

        System.out.println("After Ali moved:    " + bookings);

        // Part F
        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));

        if (broken.contains(new Seat('E', 1))) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(new Seat('E', 1), "Hana");
            System.out.println("Hana -> E1:         booked");
        }

        // Part G
        System.out.println("Seats booked:       " + bookings.size());

        // GIVEN - prints the seating chart
        System.out.println();
        printChart(bookings, broken);
    }

    // GIVEN - do not change
    static void printChart(HashMap<Seat, String> bookings, HashSet<Seat> broken) {
        System.out.println("     1  2  3  4  5  6");
        for (char row = 'A'; row <= 'E'; row++) {
            System.out.print(row + "  ");
            for (int number = 1; number <= 6; number++) {
                Seat seat = new Seat(row, number);
                if (bookings.containsKey(seat)) {
                    System.out.print("  X");
                } else if (broken.contains(seat)) {
                    System.out.print("  #");
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
    }
}


/*
 QUESTIONS - write your answers here

Q1.Why did we have to override hashcode() and equals()?

 Because HashMap uses hashCode() to find the bucket and equals() to compare keys. We need them to recognize different Seat objects representing the same seat.


Q2. Delete your hashCode() but keep equals(), and run again.
     What happens? Why?

     The program may produce incorrect results because different Seat objects can have different hash codes, even if equals() considers them equal.
 
   
Q3. Why are row and number final? What could go wrong if the number
     of a booked seat could be changed after it was put in the map?

     To prevent changing a seat's information after adding it to the HashMap. Changing its values could make the HashMap unable to find the key correctly.
 
 
Q4. Change equals(Object o) to equals(Seat o). What happens?
     Then also remove @Override and run again. What happens now?

    Java will treat it as an overloaded method, not an overridden method. HashMap will continue using the original equals(Object o). 



BONUS. Replace the whole Seat class with this one line:
            record Seat(char row, int number) {}
            Run it. What happens, and why?

record Seat(char row, int number) {}            
*/
