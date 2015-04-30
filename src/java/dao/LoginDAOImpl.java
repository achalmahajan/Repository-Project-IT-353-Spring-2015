/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.LoginBean;

/**
 *
 * @author admin
 */
public class LoginDAOImpl {

    public String validateLogin(LoginBean aUser) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");            // driver to connect to database - this is part of jdk
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        String result = "";
        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";           // connection string, jdbc: protocol for db; derby is the db;
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String userPassword = aUser.getPassword();
            String userUserName = aUser.getUserName();
            String sqlStr = "SELECT * FROM SignUp WHERE userid = ? ";
            PreparedStatement stmt = DBConn.prepareStatement(sqlStr);
            stmt.setString(1, aUser.getUserName());
            ResultSet rs = stmt.executeQuery();
            String dbFirstName = "null";
            String dbUsername;
            String dbPassword;
            String dbType;
            if (rs.next()) {
                dbFirstName = rs.getString("firstName");
                dbUsername = rs.getString("userid");
                dbPassword = rs.getString("Password");
                dbType = rs.getString("LoginType");
                if (dbUsername.equals(userUserName) && dbPassword.equals(userPassword)) 
                {
                    if (dbType.equals("student")) {
                        aUser.setFirstName(dbFirstName);
                        result = "student";
                    } else {
                        aUser.setFirstName(dbFirstName);
                        result = "professor";
                    }
                }else{
                    result = "invalid";
                    
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;

    }

}
