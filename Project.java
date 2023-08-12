import java.util.*;

class User {
    private String username;
    private String password;

    public User(String un, String pw) {
        username = un;
        password = pw;
    }

    public String getName() {
        return username;
    }

    public String getPass() {
        return password;
    }
}

class Register {
    String name;
    int trainNumber;
    String trainName;
    String classType;
    String destination;
    String startPoint;
    String date;

    Scanner sc = new Scanner(System.in);

    /**
     * Method to set the train details for the registration
     */
    public void setTrain() {
        System.out.print("\033[H\033[2J"); // to clear the screen
        System.out.flush();
        System.out.print("\t\t\t\t\t MENU \n\t\t\t1. A1 \t 2. A2 \t3. A3\n" +
                "\t\t\t4. B1 \t 5. B2 \t 6. B3\n" +
                "\t\t\t7. C1 \t 8. C2 \t 9. C3\n");
        System.out.print("\t\t\tEnter your train number : ");
        int n = sc.nextInt();
        switch (n) {
            case 1:
                trainName = "A1";
                trainNumber = 1;
                break;
            case 2:
                trainName = "A2";
                trainNumber = 2;
                break;
            case 3:
                trainName = "A3";
                trainNumber = 3;
                break;
            case 4:
                trainName = "B1";
                trainNumber = 4;
                break;
            case 5:
                trainName = "B2";
                trainNumber = 5;
                break;
            case 6:
                trainName = "B3";
                trainNumber = 6;
                break;
            case 7:
                trainName = "C1";
                trainNumber = 7;
                break;
            case 8:
                trainName = "C2";
                trainNumber = 8;
                break;
            case 9:
                trainName = "C3";
                trainNumber = 9;
                break;
            default:
                System.out.println("Wrong choice entered!!!");
                trainNumber = -1;
        }
        if (trainNumber != -1) // for successful allotment of train
        {
            sc.nextLine(); // Consume the newline character
            System.out.print("Enter your name: ");
            name = sc.nextLine();
            System.out.print("Enter date of departure: ");
            date = sc.nextLine();
            System.out.print("Enter your start point (Source): ");
            startPoint = sc.nextLine();
            System.out.print("Enter your destination: ");
            destination = sc.nextLine();
            System.out.print("Enter your class type (AC or non AC): ");
            classType = sc.next();
        } else {
            System.out.println("Please try again later!!!");
        }
    }

    /**
     * Method to display the details of the reservation
     */
    public void display() {
        System.out.print("\033[H\033[2J"); // to clear the screen
        System.out.flush();
        System.out.println("\n\n\t\tDetails are as follows :- " +
                "\n\t Name : " + name +
                "\n\t Date of departure : " + date +
                "\n\t Source : " + startPoint +
                "\n\t Destination : " + destination +
                "\n\t Train name : " + trainName +
                "\n\t Train number : " + trainNumber +
                "\n\t Class type : " + classType);
    }
}

public class Project {
    static ArrayList<User> users = new ArrayList<>();
    static Map<String, LinkedList<Register>> userReservations = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static String currentUser = null;

    public static void main(String[] args) {
        char choice;
        do {
            System.out.print("\033[H\033[2J"); // to clear the screen
            System.out.flush();
            System.out.println("\t======= MENU =======");
            System.out.println("\t 1. Registration");
            System.out.println("\t 2. Existing user");
            System.out.println("\t 3. Exit");
            System.out.print("\n\t Enter your choice: ");
            int n = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (n) {
                case 1:
                    newUser();
                    break;
                case 2:
                    existingUser();
                    break;
                case 3:
                    System.out.println("\t\tThank you for using the Online Reservation System. Goodbye!");
                    return;
                default:
                    System.out.println("\n\t Wrong choice entered!!!");
            }
            System.out.print("\n\n\t\tDo you want to continue(y/n) ? : ");
            choice = sc.next().charAt(0);
            sc.nextLine();
        } while (choice == 'y');
    }

    /**
     * Method to register a new user
     */
    static void newUser() {
        System.out.print("\n\tEnter your username: ");
        String username = sc.nextLine();
        // Check if the username already exists
        for (User user : users) {
            if (user.getName().equals(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                return;
            }
        }
        System.out.print("\tEnter your password: ");
        String pass = sc.nextLine();
        User user = new User(username, pass);
        users.add(user);
        System.out.println("\tRegistration successful!");
    }

    /**
     * Method to check if the user login is valid
     */
    public static boolean login(String name, String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPass().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for existing users
     */
    static void existingUser() {
        System.out.print("\n\tEnter your username: ");
        String username = sc.nextLine();
        System.out.print("\tEnter your password: ");
        String password = sc.nextLine();

        if (login(username, password)) {
            System.out.println("\tLogin successful!");
            currentUser = username;
            userSession();
        } else {
            System.out.println("Invalid login credentials.");
        }
    }

    /**
     * Method for user session after successful login
     */
    static void userSession() {
        char choice;
        do {
            System.out.print("\033[H\033[2J"); // to clear the screen
            System.out.flush();
            System.out.println("\t======= MENU =======");
            System.out.println("\t 1. Make a new reservation");
            System.out.println("\t 2. See your details");
            System.out.println("\t 3. Cancellation");
            System.out.println("\t 4. Back");
            System.out.print("\n\t Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); // Consume the newline character after reading the choice

            switch (ch) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    see();
                    break;
                case 3:
                    cancel();
                    break;
                case 4:
                    currentUser = null;
                    return;
                default:
                    System.out.println("\n\t Wrong choice entered!!!");
            }
            System.out.print("\n\n\t\tDo you want to continue(y/n) ? : ");
            choice = sc.next().charAt(0);
            sc.nextLine();
        } while (choice == 'y');
    }

    /**
     * Method to make a new reservation
     */
    static void makeReservation() {
        Register res = new Register();
        res.name = currentUser; // Set the name for the registration
        res.setTrain();

        if (!userReservations.containsKey(currentUser)) {
            userReservations.put(currentUser, new LinkedList<>());
        }
        userReservations.get(currentUser).add(res);

        System.out.println("Reservation created successfully!");
    }

    /**
     * Method to see the details of the reservations
     */
    static void see() {
        LinkedList<Register> userReservationsList = userReservations.get(currentUser);
        if (userReservationsList == null || userReservationsList.isEmpty()) {
            System.out.println("\tNo reservations found for user " + currentUser);
            return;
        }

        System.out.println("\tYour Reservations:");
        int count = 1;
        for (Register reservation : userReservationsList) {
            System.out.println(count + ". ");
            reservation.display();
            count++;
        }
    }

    /**
     * Method to cancel a reservation
     */
    static void cancel() {
        LinkedList<Register> userReservationsList = userReservations.get(currentUser);
        if (userReservationsList == null || userReservationsList.isEmpty()) {
            System.out.println("\tNo reservations found for user " + currentUser);
            return;
        }

        System.out.println("\tYour Reservations:");
        int count = 1;
        Iterator<Register> iterator = userReservationsList.iterator();
        while (iterator.hasNext()) {
            Register reservation = iterator.next();
            System.out.println(count + ". ");
            reservation.display();
            count++;

            System.out.print("\tDo you want to cancel this reservation? (Y/N): ");
            char ch = sc.next().charAt(0);
            sc.nextLine(); // Consume the newline character after reading the choice

            if (ch == 'Y' || ch == 'y') {
                iterator.remove();
                System.out.println("\tReservation has been canceled.");
            }
        }
    }
}
