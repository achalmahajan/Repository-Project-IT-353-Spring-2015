/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.SignUpModel;

/**
 *
 * @author it3530123
 */
public class SignDAOImpl {

    public int createProfile(SignUpModel aModel) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String insertString;

                insertString = "INSERT INTO ITKSTU.SignUpApproval VALUES (?,?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = DBConn.prepareStatement(insertString);

                stmt.setString(1, aModel.getFirstName());
                stmt.setString(2, aModel.getLastName());
                stmt.setString(3, aModel.getUserName());
                stmt.setString(4, aModel.getPassword());
                stmt.setString(5, aModel.getEmail());
                stmt.setString(6, aModel.getSecurityQuestion());
                stmt.setString(7, aModel.getSecurityAnswer());
                stmt.setString(8, aModel.getLoginType());
                stmt.setString(9, aModel.getAccountReason());

                rowCount = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }
    
   public boolean checkUser(String inputUserId) {
       Connection DBConn = null;
       try {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
       } catch (ClassNotFoundException e) {
           System.err.println(e.getMessage());
           System.exit(0);
       }
       boolean valid = false;
       try {
           DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
           String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
           DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

           String query = "SELECT userID FROM Signup WHERE userid = '" + inputUserId + "'";

           Statement stmt = DBConn.createStatement();
           ResultSet rs = stmt.executeQuery(query);
           
           while (rs.next()) {
               String dbUserId = rs.getString("userid");
               valid = inputUserId.equalsIgnoreCase(dbUserId);
           }
           rs.close();
           stmt.close();
       } catch (Exception e) {
           System.err.println("ERROR: Problems with SQL select");
           e.printStackTrace();
       }
       try {
           DBConn.close();
       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }
       return valid;
   }
   
   
   
   
   /*
   public boolean validateUserId(String userId) {
       Connection DBConn = null;
       try {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
       } catch (ClassNotFoundException e) {
           System.err.println(e.getMessage());
           System.exit(0);
       }
       boolean valid = false;
       try {
           DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
           String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
           DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

           String query = "SELECT * FROM RepositoryDB.Signup ";
           query += "WHERE userid = '" + userId+ "'";

           Statement stmt = DBConn.createStatement();
           ResultSet rs = stmt.executeQuery(query);
           String userid = userId;
           
           while (rs.next()) {
               String dbUserId = rs.getString("userid");
               String dbPassword = rs.getString("password");
               valid = userid.equalsIgnoreCase(dbUserId) ;
           }
           rs.close();
           stmt.close();
       } catch (Exception e) {
           System.err.println("ERROR: Problems with SQL select");
           e.printStackTrace();
       }
       try {
           DBConn.close();
       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }
       return valid;
   }
   */
}
