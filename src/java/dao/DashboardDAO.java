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
import model.DashboardBean;

/**
 *
 * @author it3530123
 */
public class DashboardDAO {

    public ArrayList findAll() {

        String sqlStr = "SELECT * FROM StudentProject ORDER BY viewNumber DESC";
        ArrayList aProjectCollection = selectFromDB(sqlStr);
        return aProjectCollection;
    }

    private ArrayList selectFromDB(String sqlStr) {
        ArrayList aSearchBeanCollection = new ArrayList();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");            // driver to connect to database - this is part of jdk
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";            // connection string, jdbc: protocol for db; derby is the db;
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            DashboardBean aDashboardBean = new DashboardBean();
            while (rs.next()) {
                aDashboardBean.setUserName(rs.getString("userID"));
                aDashboardBean.setProjectName(rs.getString("projectName"));
                aDashboardBean.setViewNumber(rs.getString("viewNumber"));
                aDashboardBean.setDownloadNumber(rs.getString("downloadedNumber"));
                
                aSearchBeanCollection.add(aDashboardBean);
                aDashboardBean = new DashboardBean();
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aSearchBeanCollection;
    }
    
    public ArrayList findAll2() {

        String sqlStr = "SELECT * FROM StudentProject ORDER BY downloadedNumber DESC";
        ArrayList aProjectCollection = selectFromDB2(sqlStr);
        return aProjectCollection;
    }

    private ArrayList selectFromDB2(String sqlStr) {
        ArrayList aSearchBeanCollection = new ArrayList();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");            // driver to connect to database - this is part of jdk
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";            // connection string, jdbc: protocol for db; derby is the db;
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            DashboardBean aDashboardBean = new DashboardBean();
            while (rs.next()) {
                aDashboardBean.setUserName(rs.getString("userID"));
                aDashboardBean.setProjectName(rs.getString("projectName"));
                aDashboardBean.setViewNumber(rs.getString("viewNumber"));
                aDashboardBean.setDownloadNumber(rs.getString("downloadedNumber"));
                
                aSearchBeanCollection.add(aDashboardBean);
                aDashboardBean = new DashboardBean();
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aSearchBeanCollection;
    }
}
