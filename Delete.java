import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Delete extends JFrame {
    public Delete(int k) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("DELETE * from products where id == k");
        JOptionPane.showMessageDialog(null, "Product deleted successfully!");


    }
}
