import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.ActionListener;
public class DonorDetails implements ActionListener {
    JTextField textfield1, textfield2, textfield3,textfield5;
    JFrame frame = new javax.swing.JFrame();
    JLabel label,label1,label2,label3,label4,label5;
    JComboBox combobox;
    JButton button, button1, button2;

    DonorDetails() {
        label = new javax.swing.JLabel("Donor Details");
        label.setFont(new Font("Monotype Corsiva", Font.BOLD, 45));
        label.setBounds(550, 20, 500, 40);

        frame.add(label);
        frame.setTitle("Donor details");
        frame.setLayout(null);
        frame.setSize(800, 500);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new javax.swing.JButton("Add Donor");
        button.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        button.setBounds(100, 300, 200, 30);
        frame.add(button);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);

        button1 = new javax.swing.JButton("Search");
        button1.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        button1.setBounds(100, 350, 200, 30);
        frame.add(button1);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.DARK_GRAY);
        button1.addActionListener(this);
        //  button1.addActionListener(this);

        //public void actionPerformed(ActionEvent ae){
        //   if (ae.getSource() == button1) {
        //        Search search new Search();
        //    }
        //}
        label1 = new javax.swing.JLabel("Name");
        label1.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label1.setBounds(450, 200, 150, 40);
        // label1.setForeground(Color.WHITE);
        frame.add(label1);

        textfield1 = new javax.swing.JTextField();
        textfield1.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        textfield1.setBounds(650, 200, 400, 30);
        frame.add(textfield1);

        label2 = new javax.swing.JLabel("Age");
        label2.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label2.setBounds(450, 250, 150, 40);
        // label1.setForeground(Color.WHITE);
        frame.add(label2);

        textfield2 = new javax.swing.JTextField();
        textfield2.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        textfield2.setBounds(650, 250, 400, 30);
        frame.add(textfield2);

        label3 = new javax.swing.JLabel("Sex");
        label3.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label3.setBounds(450, 300, 150, 40);
        // label1.setForeground(Color.WHITE);
        frame.add(label3);

        textfield3 = new javax.swing.JTextField();
        textfield3.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        textfield3.setBounds(650, 300, 400, 30);
        frame.add(textfield3);

        label4 = new javax.swing.JLabel("Blood Group");
        label4.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label4.setBounds(450, 350, 150, 40);
        // label1.setForeground(Color.WHITE);
        frame.add(label4);

        String bloodgroup[] = {"A+ve", "B+ve", "O+ve", "AB+ve", "A-ve", "B-ve", "O-ve", "AB-ve"};
        combobox = new JComboBox(bloodgroup);
        combobox.setBounds(650, 350, 400, 30);
        combobox.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        combobox.setLayout(null);
        frame.add(combobox);

        label5 = new javax.swing.JLabel("Contact No");
        label5.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label5.setBounds(450, 400, 150, 40);
        // label1.setForeground(Color.WHITE);
        frame.add(label5);

        textfield5 = new javax.swing.JTextField();
        textfield5.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        textfield5.setBounds(650, 400, 400, 30);
        frame.add(textfield5);

        button2 = new javax.swing.JButton("submit");
        button2.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        button2.setBounds(600, 550, 200, 30);
        frame.add(button2);
        button2.setBackground(Color.ORANGE);
        button2.setForeground(Color.BLACK);
        button2.addActionListener(this);

        //textfield=new javax.swing.JTextField();
        // textfield.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        //  textfield.setBounds(650,400,400,30);
        //  frame.add(textfield);
    }

    public void my_db_update(String str1, String str2, String str3, String str4,String str5) {
        String url="jdbc:mysql://localhost:3306/blood_donation_database";
        String username="root";
        String password="Silpa123@2003ts";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            Statement st = con.createStatement();
            int age = Integer.parseInt(str2); // Mark is an integer
            // Adding record
            String query1 = "INSERT INTO donor(Donor_name,Age,Sex,Blood_group,Contact_no)"
                    + "VALUES('"+ str1 +"','" + age + "','" + str3 + "','" + str4 +"','" + str5 + "')";
            st.executeUpdate(query1); // record added.
            ResultSet rs = st.getResultSet();

            JOptionPane.showMessageDialog(null,"Submitted successfully");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
        /**
         * @param e the event to be processed
         */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            Search search = new Search();
        } else if (e.getSource() == button2) {

            String name=textfield1.getText();
            String age=textfield2.getText();
            String sex=textfield3.getText();
            String blood=(String) combobox.getSelectedItem();
            String contact=textfield5.getText();
            my_db_update(name, age, sex, blood,contact);
        }
    }
}
