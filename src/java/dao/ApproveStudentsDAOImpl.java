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
import java.util.ArrayList;
import model.ApproveStudentsBean;

/**
 *
 * @author it3530123
 */
public class ApproveStudentsDAOImpl {

    public ArrayList findAll() {

        String sqlStr = "SELECT * FROM SignUpApproval";
        ArrayList aStudentsCollection = selectStudentsFromDB(sqlStr);
        return aStudentsCollection;

    }

    private ArrayList selectStudentsFromDB(String sqlStr) {
        ArrayList aStudentApproveBeanCollection = new ArrayList();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");            // driver to connect to database - this is part of jdk
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";        // connection string, jdbc: protocol for db; derby is the db;
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            String firstName, lastName, userName, email, accountReason;
            ApproveStudentsBean aApproveStudentBean;

            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                userName = rs.getString("UserID");
                email = rs.getString("Email");
                accountReason = rs.getString("AccountReason");

                aApproveStudentBean = new ApproveStudentsBean(firstName, lastName, userName, email, accountReason);
                aStudentApproveBeanCollection.add(aApproveStudentBean);

            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return aStudentApproveBeanCollection;

    }
}
