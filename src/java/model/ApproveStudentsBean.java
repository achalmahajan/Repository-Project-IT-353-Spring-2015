/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author it3530123
 */
public class ApproveStudentsBean {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String accountReason;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public ApproveStudentsBean(){
        
    }

    public ApproveStudentsBean(String firstName, String lastName, String userName, String email, String accountReason) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.accountReason = accountReason;
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountReason() {
        return accountReason;
    }

    public void setAccountReason(String accountReason) {
        this.accountReason = accountReason;
    }
    
    
    
}
