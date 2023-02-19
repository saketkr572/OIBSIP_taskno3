package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; //to import date classs
//import java.sql.*;
public class Deposit extends JFrame implements ActionListener
{
    JButton deposit,back;
    JTextField amount;
    String pinnumber;
    Deposit(String pinnumber)
    {
       this.pinnumber=pinnumber;
       setLayout(null);
       ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
       Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
       ImageIcon i3=new ImageIcon(i2);
       JLabel image=new JLabel(i3);
       image.setBounds(0,0,900,900);
       add(image);
       
       JLabel text=new JLabel("Enter the amount you want to deposit");
       text.setBounds(170,300,400,20);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("System",Font.BOLD,16));
       image.add(text); 
       
       amount=new JTextField(); //Creating object for adding textfield to the label
       amount.setFont(new Font("Raleway",Font.BOLD,22));//formatting the text to be enterd in the textfield
       amount.setBounds(170,350,320,25);
       image.add(amount);
       
       deposit = new JButton("Deposit");
       deposit.setBounds(355,485,150,30);
       deposit.addActionListener(this);
       image.add(deposit);
       
       back = new JButton("Back");
       back.setBounds(355,520,150,30);
       back.addActionListener(this);
       image.add(back);
       
       
       
       setSize(900,900);
       setLocation(300,0);
       setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
       
            
        
        if(ae.getSource()==deposit)
        {
            String number=amount.getText();
            Date date=new Date();
            if(number.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit");
                
            }else 
            { 
                try
                {
        
                Conn conn=new Conn();
                String query="insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+number+"')"; //entering queries in db
                conn.s.executeUpdate(query);//executing query
                JOptionPane.showMessageDialog(null, "Rs "+number+" Deposited Successfully");//popup message after money is deposited
                setVisible(false);//current window off
                new Transaction(pinnumber).setVisible(true);//transaction window on win pinnumber
            }catch(Exception e)
            {
        
            System.out.println(e);
            }
            }
        }else if(ae.getSource()==back)
        {
           setVisible(false);
           new Transaction(pinnumber).setVisible(true); 
        }
        }
    
    public static void main(String args[])
    {
        new Deposit("");
    }
}