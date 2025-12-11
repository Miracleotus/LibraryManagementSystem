import java.sql.*;

public class UserServiceImpl {

    public void addUser(String name) throws SQLException {
        String sql = "INSERT INTO users (name) VALUES (?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, name);
        pst.executeUpdate();
        conn.close();
        System.out.println("User added successfully!");
    }

    public void viewUsers() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("user_id") + " | " +
                            rs.getString("name")
            );
        }
        conn.close();
    }
}


