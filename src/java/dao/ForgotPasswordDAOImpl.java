/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.ForgotPasswordBean;

/**
 *
 * @author it3530123
 */

public class ForgotPasswordDAOImpl {
    
     public int searchPassword(ForgotPasswordBean aModel) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        int rowCount = 0;
          try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String stringQuery;
                Statement stmt = DBConn.createStatement();
                stringQuery = "Select userID, password from SignUp where email = '"+ aModel.getEmail() + "'";
                
                String dbPassword = "";
                String dbUserName = "";
                ResultSet rs = stmt.executeQuery(stringQuery);
                
               while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                dbPassword = rs.getString("Password");
                dbUserName = rs.getString("UserID");
               }
               aModel.setPassword(dbPassword);
               aModel.setUserName(dbUserName);
                
                
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

}
