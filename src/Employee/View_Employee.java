package Employee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class ViewAllEmployees implements ActionListener {

    JFrame f;
    JLabel l1;
    JButton b2;
    JTable table;
    JScrollPane sp;

    ViewAllEmployees() {
        f = new JFrame("View All Employees");
        f.setBackground(Color.green);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 800, 600);
        l1.setLayout(null);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/view.jpg"));
        l1.setIcon(img);
        f.add(l1);

        b2 = new JButton("Cancel");
        b2.setBounds(350, 500, 100, 30);
        b2.addActionListener(this);
        l1.add(b2);

        // Column names based on Add_Employee fields
        String[] columnNames = { 
            "Name", "Father's Name", "Age", "Date Of Birth", "Address", 
            "Phone", "Email Id", "Education", "Job Post", "Aadhar No", "Employee Id" 
        };

        // Create a table model and set the column names
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create table with the model
        table = new JTable(model);
        sp = new JScrollPane(table);
        sp.setBounds(20, 20, 760, 450);
        l1.add(sp);

        // Load data from the database
        try {
            conn c1 = new conn(); // Assuming conn is your database connection class
            String query = "SELECT * FROM employee";
            ResultSet rs = c1.s.executeQuery(query);

            // Debug statement to check if the ResultSet is populated
            System.out.println("Query executed: " + query);
            
            // Populate the table model with data from the database
            while (rs.next()) {
                String name = rs.getString("name");
                String fatherName = rs.getString("fname");
                String age = rs.getString("age");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String education = rs.getString("education");
                String jobPost = rs.getString("post");
                String aadharNo = rs.getString("aadhar");
                String empId = rs.getString("emp_id");

                model.addRow(new Object[]{name, fatherName, age, dob, address, phone, email, education, jobPost, aadharNo, empId});
            }
        } catch (SQLException e) {
            // Print detailed SQL exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Database error: " + e.getMessage());
        } catch (Exception e) {
            // Print any other exceptions
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Unexpected error: " + e.getMessage());
        }

        f.setSize(800, 600);
        f.setLocation(450, 250);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            f.setVisible(false);
            new details(); // Directly instantiate details without storing it in a variable
        }
    }

    public static void main(String[] ar) {
        new ViewAllEmployees(); // Directly instantiate ViewAllEmployees without storing it in a variable
    }
}