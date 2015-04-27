/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LoginDAOImpl;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.LoginBean;

/**
 *
 * @author it3530123
 */
@Named(value = "loginController")
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private boolean loggedIn = false;
    private String response;
    private LoginBean theModel;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        theModel = new LoginBean();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LoginBean getTheModel() {
        return theModel;
    }

    public void setTheModel(LoginBean theModel) {
        this.theModel = theModel;
    }

    public void checkIfLoggedIn() {
        if (!loggedIn) {
            // Can't just return "login" as it not an "action" event (// Ref: http://stackoverflow.com/questions/16106418/how-to-perform-navigation-in-prerenderview-listener-method)
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("login?faces-redirect=true");
        }
    }

    public String validateUser() {
        LoginDAOImpl aProfileDAOImpl = new LoginDAOImpl();
        String result = aProfileDAOImpl.validateLogin(theModel);

        if ("student".equals(result)) {
            
            
            loggedIn = true;
            return "studentHome.xhtml?faces-redirect=true";
        } else if ("professor".equals(result)) {
            loggedIn = true;
            return "professor.xhtml?faces-redirect=true";
        } else if (theModel.getUserName().equals("admin") && theModel.getPassword().equals("admin")) {
            loggedIn = true;
            return "adminPage.xhtml?faces-redirect=true";
        } else {
            loggedIn = false;
            response = "Invalid username/password!";
            return "";
        }
     
    }
    public String logout() {
        loggedIn = false;
        theModel.setUserName("");
        theModel.setPassword("");
        return "index.xhtml?faces-redirect=true";

    }
}
