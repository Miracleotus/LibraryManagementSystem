import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionServiceImpl {

    public void borrowBook(int userId, int bookId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        PreparedStatement checkBook = conn.prepareStatement(
                "SELECT available FROM books WHERE book_id = ?"
        );
        checkBook.setInt(1, bookId);
        ResultSet rs = checkBook.executeQuery();

        if (!rs.next() || !rs.getBoolean("available")) {
            System.out.println("Book not available!");
            conn.close();
            return;
        }


        PreparedStatement updateBook = conn.prepareStatement(
                "UPDATE books SET available = FALSE WHERE book_id = ?"
        );
        updateBook.setInt(1, bookId);
        updateBook.executeUpdate();

        // Log transaction
        PreparedStatement log = conn.prepareStatement(
                "INSERT INTO transactions (user_id, book_id, action) VALUES (?, ?, 'BORROW')"
        );
        log.setInt(1, userId);
        log.setInt(2, bookId);
        log.executeUpdate();

        conn.close();
        System.out.println("Book borrowed successfully!");
    }

    public void returnBook(int userId, int bookId) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // Mark the book available again
        PreparedStatement updateBook = conn.prepareStatement(
                "UPDATE books SET available = TRUE WHERE book_id = ?"
        );
        updateBook.setInt(1, bookId);
        updateBook.executeUpdate();

        // Log transaction
        PreparedStatement log = conn.prepareStatement(
                "INSERT INTO transactions (user_id, book_id, action) VALUES (?, ?, 'RETURN')"
        );
        log.setInt(1, userId);
        log.setInt(2, bookId);
        log.executeUpdate();

        conn.close();
        System.out.println("Book returned successfully!");
    }
}

