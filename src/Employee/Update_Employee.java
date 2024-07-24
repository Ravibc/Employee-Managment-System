package Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Update_Employee extends Add_Employee implements ActionListener {

    JFrame f;
    JLabel id1, id2, id3, id4, id5, id6, id7, id8, id9, id10, id15;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;
    JButton b, b1;
    String id_emp;

    Update_Employee(String idaa) {
        super(0);
        f = new JFrame("Update Employee Details");
        f.setSize(900, 500);
        f.setLocationRelativeTo(null); // Center the frame on screen
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);

        id_emp = idaa;
        id15 = new JLabel();
        id15.setBounds(0, 0, 900, 500);
        id15.setLayout(null);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/add_employee.jpg"));
        id15.setIcon(img);

        id8 = new JLabel("Update Employee Detail:");
        id8.setBounds(50, 10, 500, 50);
        id8.setFont(new Font("serif", Font.ITALIC, 40));
        id8.setForeground(Color.black);
        id15.add(id8);
        f.add(id15);

        id1 = new JLabel("Name:");
        id1.setBounds(50, 100, 100, 30);
        id1.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id1);

        t1 = new JTextField();
        t1.setBounds(200, 100, 150, 30);
        id15.add(t1);

        id2 = new JLabel("Father's Name:");
        id2.setBounds(400, 100, 200, 30);
        id2.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id2);

        t2 = new JTextField();
        t2.setBounds(600, 100, 150, 30);
        id15.add(t2);

        id3 = new JLabel("Address:");
        id3.setBounds(50, 150, 100, 30);
        id3.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id3);

        t3 = new JTextField();
        t3.setBounds(200, 150, 150, 30);
        id15.add(t3);

        id4 = new JLabel("Mobile No:");
        id4.setBounds(400, 150, 100, 30);
        id4.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id4);

        t4 = new JTextField();
        t4.setBounds(600, 150, 150, 30);
        id15.add(t4);

        id5 = new JLabel("Email Id:");
        id5.setBounds(50, 200, 100, 30);
        id5.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id5);

        t5 = new JTextField();
        t5.setBounds(200, 200, 150, 30);
        id15.add(t5);

        id6 = new JLabel("Education:");
        id6.setBounds(400, 200, 100, 30);
        id6.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id6);

        t6 = new JTextField();
        t6.setBounds(600, 200, 150, 30);
        id15.add(t6);

        id7 = new JLabel("Job Post:");
        id7.setBounds(50, 250, 100, 30);
        id7.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id7);

        t7 = new JTextField();
        t7.setBounds(200, 250, 150, 30);
        id15.add(t7);

        id9 = new JLabel("Aadhar No:");
        id9.setBounds(400, 250, 100, 30);
        id9.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id9);

        t8 = new JTextField();
        t8.setBounds(600, 250, 150, 30);
        id15.add(t8);

        id10 = new JLabel("Employee Id:");
        id10.setBounds(50, 300, 150, 30);
        id10.setFont(new Font("serif", Font.BOLD, 20));
        id15.add(id10);

        t9 = new JTextField();
        t9.setBounds(200, 300, 150, 30);
        id15.add(t9);

        b = new JButton("Update");
        b.setBounds(250, 400, 100, 30);
        b.addActionListener(this);
        id15.add(b);

        b1 = new JButton("Cancel");
        b1.setBounds(450, 400, 100, 30);
        b1.addActionListener(this);
        id15.add(b1);

        showData(idaa);

        f.setVisible(true);
    }

    void showData(String s) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tables", "root", "Ravi@123");
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM employee WHERE emp_id = ?")) {
            pst.setString(1, s);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                t1.setText(rs.getString("name"));
                t2.setText(rs.getString("fname"));
                t3.setText(rs.getString("address"));
                t4.setText(rs.getString("phone"));
                t5.setText(rs.getString("email"));
                t6.setText(rs.getString("education"));
                t7.setText(rs.getString("post"));
                t8.setText(rs.getString("aadhar"));
                t9.setText(rs.getString("emp_id"));
            } else {
                JOptionPane.showMessageDialog(null, "Id not found");
                new Search_Employee();
                f.setVisible(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tables","root","Ravi@123");
                 PreparedStatement pst = conn.prepareStatement("UPDATE employee SET name=?, fname=?, address=?, phone=?, email=?, education=?, post=?, aadhar=?, emp_id=? WHERE emp_id=?")) {
                
                pst.setString(1, t1.getText());
                pst.setString(2, t2.getText());
                pst.setString(3, t3.getText());
                pst.setString(4, t4.getText());
                pst.setString(5, t5.getText());
                pst.setString(6, t6.getText());
                pst.setString(7, t7.getText());
                pst.setString(8, t8.getText());
                pst.setString(9, t9.getText());
                pst.setString(10, id_emp);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Successfully updated");
                    f.setVisible(false);
                    new Search_Employee();
                } else {
                    JOptionPane.showMessageDialog(null, "Update failed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating data: " + e.getMessage());
            }
        }
        if (ae.getSource() == b1) {
            f.setVisible(false);
            new details();
        }
    }

    public static void main(String[] arg) {
        new Update_Employee("example_id");
    }
}
