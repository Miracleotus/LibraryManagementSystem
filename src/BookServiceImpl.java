import java.sql.*;

public class BookServiceImpl {


    public void addBook(String title, String author) throws SQLException {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, title);
        pst.setString(2, author);
        pst.executeUpdate();
        conn.close();
        System.out.println("Book added successfully!");
    }

    public void viewBooks() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("book_id") + " | " +
                            rs.getString("title") + " | " +
                            rs.getString("author") + " | Available: " +
                            rs.getBoolean("available")
            );
        }
        conn.close();
    }

    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE book_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
        conn.close();
        System.out.println("Book deleted successfully!");
    }
}


