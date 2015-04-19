/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author it3530123
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean implements Serializable {
    
    private static String userName;
    private static String password;
    private static String firstName;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
  
    public LoginBean(String firstName, String userName, String password) {
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    } 

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    

}
