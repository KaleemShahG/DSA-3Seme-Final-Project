import java.util.*;

class Guest {
    String name;
    int age;
    String phoneNumber;
    double bill;

    public Guest(String name, int age, String phoneNumber, double bill) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Guest{name='" + name + "', age=" + age + ", phoneNumber='" + phoneNumber + "', bill=" + bill + "}";
    }
}

class HotelManagementSystem {
    private Stack<Integer> availableRooms = new Stack<>();
    private LinkedList<Integer> bookedRooms = new LinkedList<>();
    private Map<Integer, Guest> roomGuests = new HashMap<>();
    private ArrayList<Integer> allRooms = new ArrayList<>();

    public HotelManagementSystem(int totalRooms) {
        for (int i = 1; i <= totalRooms; i++) {
            availableRooms.push(i);
            allRooms.add(i);
        }
    }

    public void bookRoom(String name, int age, String phoneNumber, double bill) {
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }
        int roomNumber = availableRooms.pop();
        bookedRooms.add(roomNumber);
        roomGuests.put(roomNumber, new Guest(name, age, phoneNumber, bill));
        System.out.println("Room " + roomNumber + " booked successfully for " + name);
    }

    public void checkIn(int roomNumber) {
        if (roomGuests.containsKey(roomNumber)) {
            System.out.println("Guest checked in to room " + roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " is not booked yet.");
        }
    }

    public void checkOut(int roomNumber) {
        if (roomGuests.containsKey(roomNumber)) {
            bookedRooms.remove(Integer.valueOf(roomNumber));
            availableRooms.push(roomNumber);
            System.out.println("Guest checked out. Total Bill: " + roomGuests.get(roomNumber).bill);
            roomGuests.remove(roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " is not currently booked.");
        }
    }
`
    public void displayAvailableRooms() {
        System.out.println("Available rooms: " + availableRooms);
    }

    public void displayBookedRooms() {
        System.out.println("Booked rooms: " + bookedRooms);
    }

    public void displayGuests() {
        for (Map.Entry<Integer, Guest> entry : roomGuests.entrySet()) {
            System.out.println("Room " + entry.getKey() + " - " + entry.getValue());
        }
    }
}

public class HotelManagementApp {
    public static void main(String[] args) {
        HotelManagementSystem hms = new HotelManagementSystem(5);

        hms.bookRoom("Alice", 28, "1234567890", 500.0);
        hms.bookRoom("Bob", 35, "0987654321", 750.0);

        hms.displayAvailableRooms();
        hms.displayBookedRooms();
        hms.displayGuests();

        hms.checkIn(5);
        hms.checkOut(5);

        hms.displayAvailableRooms();
    }
}
