import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        BookServiceImpl bookDAO = new BookServiceImpl();
        UserServiceImpl userDAO = new UserServiceImpl();
        TransactionServiceImpl ts = new TransactionServiceImpl();

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. View Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    bookDAO.addBook(title, author);
                    break;

                case 2:
                    bookDAO.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter username: ");
                    String name = sc.nextLine();
                    userDAO.addUser(name);
                    break;

                case 4:
                    userDAO.viewUsers();
                    break;

                case 5:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    ts.borrowBook(uid, bid);
                    break;

                case 6:
                    System.out.print("Enter User ID: ");
                    uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    bid = sc.nextInt();
                    ts.returnBook(uid, bid);
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}