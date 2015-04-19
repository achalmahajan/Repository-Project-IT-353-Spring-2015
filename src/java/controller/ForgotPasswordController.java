/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ForgotPasswordDAOImpl;
import java.io.Serializable;
import javamailapp.ForgotPasswordMailApp;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.ForgotPasswordBean;

/**
 *
 * @author it3530123
 */
@Named(value = "forgotPasswordController")
@SessionScoped
public class ForgotPasswordController implements Serializable{
    
    private ForgotPasswordBean forgotModel;
    private String result="";

    public ForgotPasswordController() {
        this.forgotModel = new ForgotPasswordBean();
    }

    
    public ForgotPasswordBean getForgotModel() {
        return forgotModel;
    }

    public void setForgotModel(ForgotPasswordBean forgotModel) {
        this.forgotModel = forgotModel;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    /**
     *
     * @return
     */
    public String forgotPassword() {
        ForgotPasswordDAOImpl aForgotDAOImpl = new ForgotPasswordDAOImpl();
        int a = aForgotDAOImpl.searchPassword(forgotModel);
        if (a == 0) {

            ForgotPasswordMailApp.mailapp(forgotModel);
            
            result = " Please  check your e-mail";
            
        }
        return "login.xhtml?faces-redirect=true";
        
      }
    
}
