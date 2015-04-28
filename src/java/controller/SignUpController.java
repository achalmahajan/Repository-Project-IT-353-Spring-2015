/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SignDAOImpl;
import isu.ISUSMS_Service;
import java.io.IOException;
import java.io.Serializable;
import javamailapp.SignUpMailApp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.WebServiceRef;
import model.SignUpModel;

/**
 *
 * @author it3530123
 */
@ManagedBean
@SessionScoped
public class SignUpController implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/gfish.it.ilstu.edu_8080/ISUTextSMS/ISUSMS.wsdl")
    private ISUSMS_Service service;

    private SignUpModel theModel;
    private String result = "";
    private String errorResponse = "";
    private String userCheck = "";
  

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

    public String getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(String errorResponse) {
        this.errorResponse = errorResponse;
    }
    
    

    public SignUpController() {
        theModel = new SignUpModel();
    }

    public String signUp() throws IOException {
        SignDAOImpl aProfileDAOImpl = new SignDAOImpl();
        errorResponse = theModel.validate();
        if(!errorResponse.isEmpty()){
            return "signUp.xhtml?faces-redirect=true";
        }
        int a = aProfileDAOImpl.createProfile(theModel);
        if (a == 1) {
            String msg = "Thank you for Signing Up. Your account has been send for approval";
            String provider = theModel.getProvider();
            String number = theModel.getPhoneNumber();
            sendSMS(provider, number, msg);

            SignUpMailApp.mailapp(theModel);
            result = " Thank you for signing with us!!! <br>";
            result += "Your First name is : <b> " + theModel.getFirstName() + "</b><br>";
            result += "Your Last name is : <b> " + theModel.getLastName() + "</b><br>";
            result += "Your Email-id is :<b> " + theModel.getEmail() + "</b><br>";
            result += "Your User-id is : <b> " + theModel.getUserName() + "</b><br>";
            result += "Your Security question is : <b> " + theModel.getSecurityQuestion() + "</b><br>";
            result += "Your Security answer is : <b> " + theModel.getSecurityAnswer() + "</b><br>";
            result += "Your reason for account is :<b> " + theModel.getAccountReason() + "</b><br> <br>";
            result += "<b> Your account is been send for approval </b> ";

            return "signUpResponse.xhtml?faces-redirect=true";
        } else {
            errorResponse = "Sorry try again";
            return "signUp.xhtml?faces-redirect=true";
        }
    }
    
    public String checkUser(){
        SignDAOImpl aProfileDAOImpl = new SignDAOImpl();
        boolean value = aProfileDAOImpl.checkUser(theModel.getUserName());
        if(value = true){
            result = "Not available";
        }else{
            result = "available";
        }
        return result;
    }

    private String sendSMS(java.lang.String provider, java.lang.String number, java.lang.String message) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        isu.ISUSMS port = service.getISUSMSPort();
        return port.sendSMS(provider, number, message);
    }
    
}
