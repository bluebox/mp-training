package JDBC;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private final static  String  file="jdbc:mysql/localhost:3306/music";

    public  static  void main(String[] args){

        String userName= JOptionPane.showInputDialog(null,"enter the user name");
        JPasswordField passwordField= new JPasswordField();
        int a=JOptionPane.showConfirmDialog(null,passwordField,"enterPassword",JOptionPane.OK_CANCEL_OPTION);
        final char[] password=(a==(JOptionPane.OK_OPTION)?passwordField.getPassword():null);


        try(Connection connection= DriverManager.getConnection(file,userName, String.valueOf(passwordField))){
            System.out.println("yes its working!!!");
        }catch (SQLException e){
            System.out.println("something happend");
            throw new RuntimeException(e);
        }

    }

}
