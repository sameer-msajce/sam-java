import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Library {
    private HashMap<String, Book> inventory = new HashMap<>();
    private HashMap<String, Member> members = new HashMap<>();

    public void addBook(Book book) {
        inventory.put(book.getId(), book);
        logOperation("Book Added: " + book.getId());
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
        logOperation("Member Added: " + member.getMemberId());
    }

    public void issueBook(String bookId, String memberId) throws BookNotAvailableException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null) {
            throw new BookNotAvailableException("Book not found.");
        }
        if (member == null) {
            throw new BookNotAvailableException("Member not found.");
        }
        if (book.isIssued()) {
            throw new BookNotAvailableException("Book is already issued.");
        }

        book.setIssued(true);
        member.borrowBook(bookId);

        logOperation("Book Issued: " + bookId + " to Member: " + memberId);
    }

    public void returnBook(String bookId, String memberId, int daysLate)
            throws InvalidReturnException {

        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null || member == null || !member.getBorrowedBookIds().contains(bookId)) {
            throw new InvalidReturnException("Invalid return attempt.");
        }

        book.setIssued(false);
        member.returnBook(bookId);

        int lateFee = daysLate * 2;

        logOperation("Book Returned: " + bookId + " by Member: " + memberId +
                " | Late Fee: ₹" + lateFee);
        System.out.println("Book returned successfully! Late fee: ₹" + lateFee);
    }

    public void showInventory() {
        System.out.println("\n----- Library Inventory -----");
        if (inventory.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            inventory.values().forEach(System.out::println);
        }
        System.out.println("------------------------------\n");
    }

    public void logOperation(String message) {
        try (FileWriter fw = new FileWriter("library_log.txt", true)) {
            fw.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error logging operation.");
        }
    }
}
