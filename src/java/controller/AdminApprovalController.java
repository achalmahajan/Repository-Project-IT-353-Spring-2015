/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdminApprovalDAOImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javamailapp.AdminRejectMailApp;
import javax.faces.bean.ManagedBean;
import model.AdminApprovalBean;

/**
 *
 * @author it3530123
 */
@Named(value = "adminApprovalController")
@ManagedBean
@SessionScoped
public class AdminApprovalController implements Serializable {

    private AdminApprovalBean theAdminApprovalBean;
    private String result="";
    
    /**
     * Creates a new instance of AdminApprovalController
     */
    public AdminApprovalController() {
        theAdminApprovalBean = new AdminApprovalBean();
    }

    public AdminApprovalBean getTheAdminApprovalBean() {
        return theAdminApprovalBean;
    }

    public void setTheAdminApprovalBean(AdminApprovalBean theAdminApprovalBean) {
        this.theAdminApprovalBean = theAdminApprovalBean;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public String approve(String userName) {
        AdminApprovalDAOImpl aAdminApprovalDAOImpl = new AdminApprovalDAOImpl();
        String selectedName = userName;
        int a = aAdminApprovalDAOImpl.createProfile(selectedName);
        if(a==0){
            result = "Student account is been approved";
        }else{
            result = "not approved";
        }
        return result;
    }
    
    public String reject(String userName){
        
        AdminApprovalDAOImpl aAdminApprovalDAOImpl = new AdminApprovalDAOImpl();
        String selectedName = userName;
        theAdminApprovalBean.setUserName(selectedName);
        String rejectReason = theAdminApprovalBean.getRejectReason();
        
        boolean a = aAdminApprovalDAOImpl.rejectProfile(theAdminApprovalBean);
        if(a == true){
            String test = theAdminApprovalBean.getEmail();
            AdminRejectMailApp.mailapp(theAdminApprovalBean);
            result = "Student account is been deleted";
        }else{
            result = "not deleted";
        }
        return result;
    }
    
    
    
    
  
    
}
