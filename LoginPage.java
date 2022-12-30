import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.util.*;

class LoginPage {

    JPanel panel;
    JFrame jf;
    JLabel label1,label2;
    JButton login;
    JTextField textfield1,textfield2,textfield3,passwordfield;

    public LoginPage() {
        initComponents();
        handlingEvents();
    }

    public void initComponents() {
        jf=new javax.swing.JFrame();
         //Color c1 = new Color(0,0,255);
       // jf.getContentPane().setBackground(Color.DARK_GRAY);
        jf.setTitle("Admin Login");
        jf.setLayout(null);
        jf.setSize(800,500);

        JScrollPane scrollBar=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jf.add(scrollBar);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setVisible(true);
       // jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1=new javax.swing.JLabel("Admin Login");
        label1.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
        label1.setBounds(550,20,500,40);
        // label1.setForeground(Color.WHITE);
        jf.add(label1);

        label1=new javax.swing.JLabel("Username");
        label1.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label1.setBounds(450,250,150,40);
       // label1.setForeground(Color.WHITE);
        jf.add(label1);

        textfield1=new javax.swing.JTextField();
        textfield1.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        textfield1.setBounds(650,250,150,30);
        jf.add(textfield1);

        label2=new javax.swing.JLabel("Password");
        label2.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label2.setBounds(450,300,100,40);
        // label1.setForeground(Color.WHITE);
        jf.add(label2);

        passwordfield=new javax.swing.JPasswordField();
        passwordfield.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        passwordfield.setBounds(650,300,150,30);
        jf.add(passwordfield);

        login=new javax.swing.JButton("Login");
        login.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        login.setBounds(550,400,100,30);
        login.setBackground(Color.ORANGE);
        login.setForeground(Color.BLACK);
        jf.add(login);
    }
    public void handlingEvents() {
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String un = textfield1.getText();
                String ui = passwordfield.getText();
                boolean valid = false;
                String url="jdbc:mysql://localhost:3306/blood_donation_database";
                String username="root";
                String password="Silpa123@2003ts";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection(url,username,password);
                    Statement st = c.createStatement();
                    st.execute("SELECT user_id,user_password FROM admin WHERE user_id='"+un+"' AND user_password='"+ui+"'");
                    ResultSet rs = st.getResultSet();
                    while(rs.next())
                    {
                        if(rs != null) {
                            valid = true;
                            username= rs.getString(1);
                            password= rs.getString(2);
                        }
                        else
                            valid = false;
                    }
                    if(valid == true)
                    {
                        DonorDetails donorDetails = new DonorDetails();
                       // JOptionPane.showMessageDialog(null,"Authenticated User");
                        //textfield1.setText("");
                       // passwordfield.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Not Authenticated User");
                    }
                    c.close();
                    st.close();

                }
                catch (Exception e) {
                    System.out.println(e);
                }

            }
        });

    }

    public static void main(String args[]) {
        LoginPage log = new LoginPage();
    }
}
