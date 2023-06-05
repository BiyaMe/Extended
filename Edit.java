import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Edit extends JFrame {
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    private JTextArea idText;
    private JTextArea nameText;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField categoryCombo;
    private JTextField shortDescriptionField;

    public Edit(int j) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
        statement = connection.createStatement();
        result = statement.executeQuery("SELECT * from products where id == j");

        JFrame jframe = new JFrame("Update Product");
        JLabel idLabel = new JLabel("Name");
        JLabel nameLabel = new JLabel("Quantity");
        idText = new JTextArea(result.getString(2));
        nameText = new JTextArea(result.getString(3));
        JLabel priceLabel = new JLabel("Location");
        JLabel quantity = new JLabel("Cost");
        priceField = new JTextField(result.getString(4));
        quantityField = new JTextField(result.getString(7));
        JLabel categoryLabel = new JLabel("Category");
        categoryCombo = new JTextField(result.getString(5));
        JLabel shortDescriptionLabel = new JLabel("Description");
        shortDescriptionField = new JTextField(result.getString(6));
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idText.getText();
                    String name = nameText.getText();
                    String price = priceField.getText();
                    String quantity = quantityField.getText();
                    String category = categoryCombo.getText();
                    String description = shortDescriptionField.getText();

                    // build the SQL query to update the data in the database
                    String query = "UPDATE products SET name='" + name + "', price='" + price + "', quantity='" + quantity + "', category='" + category + "', description='" + description + "' WHERE id='" + id + "'";

                    statement.executeUpdate(query);

                    // show a message dialog to confirm that the update was successful
                    JOptionPane.showMessageDialog(null, "Product updated successfully!");

                    // close the connection
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jframe.setLayout(new GridLayout(7,2,20,20));
        jframe.add(idLabel);
        jframe.add(nameLabel);
        jframe.add(idText);
        jframe.add(nameText);
        jframe.add(priceLabel);
        jframe.add(quantity);
        jframe.add(priceField);
        jframe.add(quantityField);
        jframe.add(shortDescriptionLabel);
        jframe.add(categoryLabel);
        jframe.add(shortDescriptionField);
        jframe.add(categoryCombo);
        jframe.add(submitButton);

        jframe.setSize(500, 500);
        jframe.setVisible(true);
    }
}