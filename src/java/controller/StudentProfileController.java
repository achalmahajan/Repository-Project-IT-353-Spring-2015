/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentProfileDAOImpl;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import model.StudentProfileBean;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author it3530123
 */
@ManagedBean
@SessionScoped
public class StudentProfileController implements Serializable {
    
    private StudentProfileBean theModel;
    private String result = "";
    
    public StudentProfileController() {
        theModel = new StudentProfileBean();
    }
    
    public StudentProfileBean getTheModel() {
        return theModel;
    }
    
    public void setTheModel(StudentProfileBean theModel) {
        this.theModel = theModel;
    }
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    public String documentUpload() throws IOException {
        //theModel = new StudentProfileBean();
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        theModel.setName(name);
        StudentProfileDAOImpl aStudentDAOImpl = new StudentProfileDAOImpl();
        int a = aStudentDAOImpl.uploadDocument(theModel);
        if (a == 1) {
            result += "Uploaded <br>";
        } else {
            return "studentHome.xhtml";
        }
        return result;
    }
    
    public String updateProfile() throws IOException {
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        theModel.setName(name);
        StudentProfileDAOImpl theStudentProfileDAO = new StudentProfileDAOImpl();
        int a = theStudentProfileDAO.uploadProfile(theModel);
        if (a == 1) {
            result = "Uploaded";
        }
        return result;
    }
    
    public DefaultStreamedContent download(){
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        theModel.setName(name);
        StudentProfileDAOImpl theStudentProfileDAO = new StudentProfileDAOImpl();
        DefaultStreamedContent a = theStudentProfileDAO.downloadFileFromDB(theModel);
        
            return theModel.getDownloadFileProposal();
        
        
    }
    
    public void checkStudentProfile() throws IOException{
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        //theModel.setName(name);
        StudentProfileDAOImpl theStudentProfileDAO = new StudentProfileDAOImpl();
        this.theModel = theStudentProfileDAO.fetchStudentProfile(name);
        
    }
    
    public void checkStudentDocuments() throws IOException{
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        StudentProfileDAOImpl theStudentProfileDAO = new StudentProfileDAOImpl();
        this.theModel = theStudentProfileDAO.fetchStudentDocuments(name);
    }
}
