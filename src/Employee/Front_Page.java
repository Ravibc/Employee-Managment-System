package Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Front_Page implements ActionListener {
    JFrame f; // JFrame declaration
    JLabel id, l1;
    JButton b;

    Front_Page() {
        f = new JFrame("Employee Management System"); // Initialize the JFrame
        f.setBackground(Color.red);
        f.setLayout(null);

        // Create and scale the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/img.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1400, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        // Use class-level l1
        l1 = new JLabel(i3);
        l1.setBounds(0, 150, 1360, 530);
        f.add(l1);

        // Button setup
        b = new JButton("CLICK HERE TO CONTINUE");
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        b.setBounds(500, 500, 300, 70);
        b.addActionListener(this);

        // Panel setup
        id = new JLabel();
        id.setBounds(0, 0, 1360, 750);
        id.setLayout(null);

        JLabel lid = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        lid.setBounds(80, 30, 1500, 100);
        lid.setFont(new Font("calibri", Font.PLAIN, 70));
        lid.setForeground(Color.BLACK);
        id.add(lid);

        id.add(b);
        f.add(id);

        f.getContentPane().setBackground(Color.WHITE);

        // Timer to blink the label
        Timer timer = new Timer(500, e -> lid.setVisible(!lid.isVisible()));
        timer.start();

        f.setVisible(true);
        f.setSize(1360, 750);
        f.setLocation(200, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b) {
            f.setVisible(false);
            new login(); // Assuming login is a class you have implemented
        }
    }

    public static void main(String[] arg) {
        new Front_Page();
    }
}
