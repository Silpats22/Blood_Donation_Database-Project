import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
public class Search implements ActionListener{
    JFrame frame = new javax.swing.JFrame();
    JFrame frame1 = new javax.swing.JFrame();
    JLabel label = new javax.swing.JLabel();
    PreparedStatement pst;
    JComboBox combobox;
    JButton button;
    static JTable table;

    String columnNames[] = {"Donor name","Age","Sex","Blood Group","Contact_no"};
    Search() {
        frame.setTitle("Search");
        frame.setLayout(null);
        frame.setSize(800, 500);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new javax.swing.JLabel("Search for available donors");
        label.setFont(new Font("Monotype Corsiva", Font.BOLD, 45));
        label.setBounds(450, 50, 500, 40);
        frame.add(label);

        label = new javax.swing.JLabel("Blood Group");
        label.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        label.setBounds(350, 250, 500, 40);
        frame.add(label);

        String blood[] = {"O+ve", "B+ve", "A+ve", "AB+ve", "A-ve", "B-ve", "O-ve", "AB-ve"};
        combobox = new JComboBox();
        combobox = new JComboBox(blood);
        combobox.setBounds(550, 250, 500, 30);
        combobox.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        combobox.setLayout(null);
        frame.add(combobox);

        button = new javax.swing.JButton("Get Data");
        button.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        button.setBounds(550, 350, 150, 30);
        frame.add(button);
        button.setBackground(Color.ORANGE);
        button.setForeground(Color.BLACK);
        button.addActionListener(this);
    }
        public void ResultData(){
            frame1 = new JFrame("Database Search Result");
            frame1.setSize(800, 500);
            frame1.setLayout(new BorderLayout());
            frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame1.setVisible(true);
            //TableModel tm = new TableModel();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
            //table = new JTable(model);
            table = new JTable();
            table.setModel(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFillsViewportHeight(true);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            String from =(String) combobox.getSelectedItem();
            String url="jdbc:mysql://localhost:3306/blood_donation_database";
            String username="root";
            String password="Silpa123@2003ts";
            String name = "";
            String age = "";
            String sex = "";
            String blood = "";
            String contact="";
            try

            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,username,password);
                Statement st = con.createStatement();
                st.execute("SELECT Blood_group FROM donor");
                ResultSet rs = st.getResultSet();
                pst = con.prepareStatement("select * from donor where Blood_group='" + from + "'");
                rs = pst.executeQuery();
                int i = 0;
                while (rs.next()) {
                    name = rs.getString("Donor_name");
                    age = rs.getString("Age");
                    sex = rs.getString("Sex");
                    blood = rs.getString("Blood_group");
                    contact = rs.getString("Contact_no");
                    model.addRow(new Object[]{name,age,sex,blood,contact});
                    i++;
                }
                if (i < 1) {
                    JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (i == 1) {
                    System.out.println(i + " Record Found");
                } else {
                    System.out.println(i + " Records Found");
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            frame1.add(scroll);
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        ResultData();
    }
}