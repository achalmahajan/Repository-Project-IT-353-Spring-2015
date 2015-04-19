/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SignDAOImpl;
import java.io.IOException;
import java.io.Serializable;
import javamailapp.SignUpMailApp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.SignUpModel;

/**
 *
 * @author it3530123
 */
@ManagedBean
@SessionScoped
public class SignUpController implements Serializable {

    private SignUpModel theModel;
    private String result = "";
  

    /**
     * @return the theModel
     */
    public SignUpModel getTheModel() {
        return theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(SignUpModel theModel) {
        this.theModel = theModel;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    public SignUpController() {
        theModel = new SignUpModel();
    }

    public String signUp() throws IOException {
        SignDAOImpl aProfileDAOImpl = new SignDAOImpl();
        int a = aProfileDAOImpl.createProfile(theModel);
        if (a == 1) {

            SignUpMailApp.mailapp(theModel);
            result = "Thank you for signing with us!!! <br>";
            result += "Your First name is : " + theModel.getFirstName() + "<br>";
            result += "Your Last name is : " + theModel.getLastName() + "<br>";
            result += "Your Email-id is : " + theModel.getEmail() + "<br>";
            result += "Your User-id is : " + theModel.getUserName() + "<br>";
            result += "Your Security question is : " + theModel.getSecurityQuestion() + "<br>";
            result += "Your Security answer is : " + theModel.getSecurityAnswer() + "<br>";
            result += "Your reason for account is :" + theModel.getAccountReason() + "<br> <br>";
            result += "Your account is been send for approval";

            //send email confirmation with an embedded images that should be unique.
            return "signUpResponse.xhtml?faces-redirect=true";
            // show resonse some more interative.
        } else {
            result = "Sorry try again";
            return "signUp.xhtml?faces-redirect=true";
        }
    }

}
