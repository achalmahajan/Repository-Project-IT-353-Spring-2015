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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.SearchBean;

/**
 *
 * @author it3530123
 */
@Named(value = "searchDAO")
@SessionScoped
public class SearchDAO implements Serializable {

    
    /**
     * Creates a new instance of SearchDAO
     */
    public SearchDAO() {
    }
    
    public ArrayList findAll(String searchKeyword) {

        String sqlStr = "SELECT * FROM StudentProject where details like '%"+ searchKeyword + "%'";
        ArrayList aProjectCollection = selectProjectFromDB(sqlStr);
        return aProjectCollection;
    }
    
    private ArrayList selectProjectFromDB(String sqlStr) {
        ArrayList aSearchBeanCollection = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");            // driver to connect to database - this is part of jdk
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/amahaja_Sp2015_RepositoryDB";         // connection string, jdbc: protocol for db; derby is the db;
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            SearchBean aSearchBean = new SearchBean();;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                //firstName = rs.getString("FirstName");
                aSearchBean.setUserName(rs.getString("UserID"));
                aSearchBean.setProjectName(rs.getString("ProjectName"));
                aSearchBean.setKeywords(rs.getString("keywords"));
                aSearchBean.setProjectAbstract(rs.getString("abstract"));
               
                aSearchBeanCollection.add(aSearchBean);
                aSearchBean = new SearchBean();
            }
        
    rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aSearchBeanCollection;
    }
}
