import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bId = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    library.addBook(new Book(bId, title, author));
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    String mId = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    library.addMember(new Member(mId, name));
                    System.out.println("Member added successfully!");
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    bId = sc.nextLine();
                    System.out.print("Enter Member ID: ");
                    mId = sc.nextLine();

                    try {
                        library.issueBook(bId, mId);
                        System.out.println("Book issued successfully!");
                    } catch (BookNotAvailableException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    bId = sc.nextLine();
                    System.out.print("Enter Member ID: ");
                    mId = sc.nextLine();
                    System.out.print("Enter Days Late: ");
                    int days = sc.nextInt();

                    try {
                        library.returnBook(bId, mId, days);
                    } catch (InvalidReturnException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    library.showInventory();
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
