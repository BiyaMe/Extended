import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class InventoryManagementPage extends JFrame {
    private JTable inventoryTable;
    private JScrollPane scrollPane;
public InventoryManagementPage() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * from products where id == 1");
        ResultSet result1 = statement.executeQuery("SELECT * from products where id == 2");
        ResultSet result2 = statement.executeQuery("SELECT * from products where id == 3");
        ResultSet result3 = statement.executeQuery("SELECT * from products where id == 4");
        int num=result.getInt(3) * result.getInt(7);
        int num1=result1.getInt(3) * result1.getInt(7);
        int num2=result1.getInt(3) * result1.getInt(7);
        int num3=result1.getInt(3) * result1.getInt(7);
        // Set up the JFrame
        setTitle("Inventory Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table
        String[] columnNames = {"Product", "Quantity", "Location", "Category", "Description", "Cost per item", "Inventory value", "Edit", "Delete"};
        Object[][] data = {
        {result.getString(2), result.getString(3), result.getString(4),result.getString(5), result.getString(6), result.getString(7), num, "Edit A", "Delete A"},
        {result1.getString(2), result1.getString(3), result1.getString(4),result1.getString(5), result1.getString(6), result1.getString(7), num1, "Edit B", "Delete B"},
        {result2.getString(2), result2.getString(3), result2.getString(4),result2.getString(5), result2.getString(6), result2.getString(7), num2, "Edit C", "Delete C"},
        {result3.getString(2), result3.getString(3), result3.getString(4),result3.getString(5), result3.getString(6), result3.getString(7), num3, "Edit D", "Delete D"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        inventoryTable = new JTable(model);

        // Add a mouse listener to the table
        inventoryTable.addMouseListener(new MouseAdapter() {
@Override
public void mouseClicked(MouseEvent e) {
        int row = inventoryTable.getSelectedRow();
        int col = inventoryTable.getSelectedColumn();
        try {
                if (col == 7 && inventoryTable.getValueAt(row, col).equals("Edit A")) {
                        Edit edit = new Edit(1);
                        //Edit.setVisible(true);
                }else if (col == 7 && inventoryTable.getValueAt(row, col).equals("Edit B")) {
                        Edit edit = new Edit(2);
                        //Edit.setVisible(true);
                }else if (col == 7 && inventoryTable.getValueAt(row, col).equals("Edit C")) {
                        Edit edit = new Edit(3);
                        //Edit.setVisible(true);
                }else if (col == 7 && inventoryTable.getValueAt(row, col).equals("Edit D")) {
                        Edit edit = new Edit(4);
                        //Edit.setVisible(true);}
                }else if (col == 8 && inventoryTable.getValueAt(row, col).equals("Delete A")) {
                        Delete delete = new Delete(1);
                }else if (col == 8 && inventoryTable.getValueAt(row, col).equals("Delete B")) {
                        Delete delete = new Delete(2);
                }else if (col == 8 && inventoryTable.getValueAt(row, col).equals("Delete C")) {
                        Delete delete = new Delete(3);
                }else if (col == 8 && inventoryTable.getValueAt(row, col).equals("Delete D")) {
                        Delete delete = new Delete(4);
                }



        } catch (SQLException ex) {
                throw new RuntimeException(ex);
        }
}});

        // Set up the scroll pane
        scrollPane = new JScrollPane(inventoryTable);
        add(scrollPane);

        // Display the JFrame
        setVisible(true);
        }

public static void main(String[] args) throws SQLException {
        InventoryManagementPage inventoryPage = new InventoryManagementPage();
        }
}