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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.StudentProfileBean;
import model.ViewStudentDocuments;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author it3530123
 */
@ManagedBean
@SessionScoped
public class StudentProfileController implements Serializable {

//    @ManagedProperty(value="#{params.transferName}")
    private String transferName;

    private StudentProfileBean theModel;
    private List<ViewStudentDocuments> profiles;
    private ViewStudentDocuments selectedProfile;
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

    public ViewStudentDocuments getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(ViewStudentDocuments selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public List<ViewStudentDocuments> getProfiles() {
        StudentProfileDAOImpl theStudentDocumentDAO = new StudentProfileDAOImpl();
        this.profiles = theStudentDocumentDAO.findAllStudentDocuments();
        return profiles;
    }

    public void setProfiles(List<ViewStudentDocuments> profiles) {
        this.profiles = profiles;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
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

    public DefaultStreamedContent download() {
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        theModel.setName(name);
        StudentProfileDAOImpl theStudentProfileDAO = new StudentProfileDAOImpl();
        DefaultStreamedContent a = theStudentProfileDAO.downloadFileFromDB(theModel);

        return theModel.getDownloadFileProposal();

    }

    public void checkStudentProfile() throws IOException {
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        //theModel.setName(name);
        StudentProfileDAOImpl theStudentProfileDAO = new StudentProfileDAOImpl();
        this.theModel = theStudentProfileDAO.fetchStudentProfile(name);

    }

    public void checkStudentDocuments() throws IOException {
        LoginController aLoginC = new LoginController();
        String name = aLoginC.getTheModel().getUserName();
        StudentProfileDAOImpl theStudentProfileDAO = new StudentProfileDAOImpl();
        this.theModel = theStudentProfileDAO.fetchStudentDocuments(name);
    }

    public void viewStudentDocument(String name) {
//        String name = getUrl();
        for (int i = 0; i < getProfiles().size(); i++) {
            if (name.equals(getProfiles().get(i).getUserName())) {
                selectedProfile = getProfiles().get(i);
            }
        }
    }

    public String open(String name) {
           return "viewStudentProfile.xhtml?userName = " + name;
       }
    
    public String getUrl() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        return url;
    }
    
    public void init() {
        // Do here your thing with those parameters.
        System.out.println(transferName);
    }
}
