/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AdminApprovalBean;

/**
 *
 * @author it3530123
 */
@Named(value = "adminApprovalDAOImpl")
@SessionScoped
public class AdminApprovalDAOImpl implements Serializable {

    /**
     * Creates a new instance of AdminApprovalDAOImpl
     */
    public AdminApprovalDAOImpl() {
    }
    String firstName, lastName, userName, password, email, securityQuestion, securityAnswer, loginType;

    //public int createProfile(AdminApprovalBean aAdminApprovalBean) {

    public int createProfile(String name) {
        AdminApprovalBean aAdminApprovalBean = null;
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
                String insertString;
                insertString = "SELECT * FROM ITKSTU.SignUpApproval where userID = '" + name + "'";
                //Statement stmt = DBConn.createStatement();
                PreparedStatement stmt = DBConn.prepareStatement(insertString);
                ResultSet rs = stmt.executeQuery();
//                String firstName, lastName, userName, password, email, securityQuestion, securityAnswer, loginType;

                while (rs.next()) {
                    // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                    // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                    firstName = rs.getString("FirstName");
                    lastName = rs.getString("LastName");
                    userName = rs.getString("UserID");
                    password = rs.getString("Password");
                    email = rs.getString("Email");
                    securityQuestion = rs.getString("securityQuestion");
                    securityAnswer = rs.getString("securityAnswer");
                    loginType = rs.getString("loginType");

                    aAdminApprovalBean = new AdminApprovalBean(firstName, lastName, userName, password, email, securityQuestion, securityAnswer, loginType);
                }

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String insertString;
                //Statement stmt = DBConn.createStatement();
                insertString = "INSERT INTO ITKSTU.SignUp VALUES (?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = DBConn.prepareStatement(insertString);
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, userName);
                stmt.setString(4, password);
                stmt.setString(5, email);
                stmt.setString(6, securityQuestion);
                stmt.setString(7, securityAnswer);
                stmt.setString(8, loginType);
                //stmt.setString(9, aAdminApprovalBean.getAccountReason());

                rowCount = stmt.executeUpdate();

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        if (loginType.equals("student")) {

            try {
                String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";
                try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                    String insertString;
                    //Statement stmt = DBConn.createStatement();
                    insertString = "INSERT INTO ITKSTU.Student (userID) VALUES (?)";

                    PreparedStatement stmt = DBConn.prepareStatement(insertString);

                    stmt.setString(1, userName);

                    rowCount = stmt.executeUpdate();

                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";
                try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                    String insertString;
                    //Statement stmt = DBConn.createStatement();
                    insertString = "INSERT INTO ITKSTU.StudentProject (userID) VALUES (?)";

                    PreparedStatement stmt = DBConn.prepareStatement(insertString);

                    stmt.setString(1, userName);

                    rowCount = stmt.executeUpdate();

                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String deleteString;
                //Statement stmt = DBConn.createStatement();
                deleteString = "delete from ITKSTU.SignUpApproval where UserID = '" + userName + "'";

                PreparedStatement stmt = DBConn.prepareStatement(deleteString);

                stmt.executeUpdate();

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

    public boolean rejectProfile(AdminApprovalBean theModel) {

        boolean result = false;
        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String insertString;
                String name = theModel.getUserName();
                insertString = "SELECT * FROM ITKSTU.SignUpApproval where userID = '" + name + "'";
                PreparedStatement stmt = DBConn.prepareStatement(insertString);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                    // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                    firstName = rs.getString("FirstName");
                    email = rs.getString("Email");

                    theModel.setFirstName(firstName);
                    theModel.setEmail(email);

                }

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {

                String deleteString;
                String name1 = theModel.getUserName();
                //Statement stmt = DBConn.createStatement();
                deleteString = "delete from ITKSTU.SignUpApproval where UserID = '" + name1 + "'";

                PreparedStatement stmt = DBConn.prepareStatement(deleteString);

                stmt.executeUpdate();
                result = true;

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }
}
