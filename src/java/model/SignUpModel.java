/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.SignDAOImpl;
import java.io.Serializable;

/**
 *
 * @author it3530123
 */
//@ManagedBean
//@SessionScoped
public class SignUpModel implements Serializable {

    private String firstName = "";
    private String lastName = "";
    private String userName = "";
    private String password = "";
    private String confirmPassword = "";
    private String email = "";
    private String securityQuestion = "";
    private String securityAnswer = "";
    private String loginType = "";
    private String accountReason = "";
//    private String userIdResult;

//    public String getUserIdResult() {
//       SignDAOImpl update=new SignDAOImpl();
//       Boolean result=update.validateUserId(getUserName());
//       if(result==false)
//       userIdResult="User Id available";
//       if(result==true)
//       userIdResult="User Id already taken, please enter a new one";
//       return userIdResult;
//    }
//
//    public void setUserIdResult(String userIdResult) {
//        this.userIdResult = userIdResult;
//    }

    public String getAccountReason() {
        return accountReason;
    }

    public void setAccountReason(String accountReason) {
        this.accountReason = accountReason;
    }
    
    /**
     * Creates a new instance of SignUpModel
    */
    
    public SignUpModel(){
        
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
    
    
    //Adding codes for validation
    public String checkUserID() {
        if (userName.length() < 2 || userName.length() > 12) {
            return "UserID must be between 2 and 12 characters";
        }
       // SignDAOImpl dao = new SignDAOImpl();
       // if(dao.exists(this)){
        //    return "UserID '"+userName+"' has already been taken";
       // }
        return "";
    }
    public String checkPassword() {
        if (password.length() < 2 || password.length() > 12) {
            return "Password must be between 2 and 12 characters";
        }
        return "";
    }
    
    public String matchPassword(){
        if (!password.equals(confirmPassword)) {
            return "Error: passwords must match.";
        }
        return "";
        
    }
    public String checkName(){
        if((firstName.equals(""))||(lastName.equals(""))){
            return "Please enter your name";
        }
        if (firstName.length() < 2 || firstName.length() > 25) {
            return "First name must be between 2 and 25 characters";
        }
        if (lastName.length() < 2 || lastName.length() > 25) {
            return "Last name must be between 2 and 25 characters";
        }
        return "";
    }
    public String checkEmail(){
        boolean result = true;
        int atPosition = email.indexOf("@");
        if (atPosition == -1 // must have an @ sign
                || atPosition == 0 // no @ at the beginning
                || atPosition == email.length() - 1) // no @ at the end
        {
            result = false;
        } else {
            atPosition = email.indexOf("@", atPosition + 1);
            if (atPosition != -1) {
                result = false; //more than one @ symbol
            }
        }
        if (result) {
            //check periods
            if(email.charAt(0)=='.'||email.charAt(email.length()-1)=='.'){
                result=false;
            }else{
                atPosition = email.indexOf("@");
                int periodPos = email.indexOf(".", atPosition+1);
                if(email.charAt(atPosition-1)=='.'||periodPos==atPosition+1||periodPos == -1){
                    result=false;
                }
            }
        }
        if(!result){
            return "Invalid email address";
        }
        return "";
    }
    
    public String validate() {
        String response = checkName();
        if (response.isEmpty()) {
            response = checkPassword();
        }
        if (response.isEmpty()) {
            response = checkEmail();
        }
        if (response.isEmpty()) {
            response = matchPassword();
        }
        return response;
    }
//Validation codes end
    
    
}
