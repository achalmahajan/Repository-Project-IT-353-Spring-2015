/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author it3530123
 */
@ManagedBean(name = "studentProfileBean", eager = true)
@SessionScoped

public class StudentProfileBean implements Serializable {

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;
    private static String userName;
    private static String name;

    private static String gender;
    private static String contactNumber;
    private static String course;
    private static String major;
    private static String semesterCompleted;

    private static String projectName;
    private static String keywords;
    private static String projectAbstract;
    private UploadedFile initialProposal;
    private UploadedFile finalProposal;
    private static String liveLink;
    
    
    DefaultStreamedContent downloadFileProposal;
    DefaultStreamedContent downloadFinalProposal;

    public StudentProfileBean() {
    }

//    public StudentProfileBean(String user) {
//        this.userName = user;
//    }
    
    public StudentProfileBean(String userName1, String projectName1, String keywords1, String projectAbstract1, UploadedFile initialProposal1, UploadedFile finalProposal1, String liveLink1) {
        userName = userName1;
        projectAbstract = projectAbstract1;
        keywords = keywords1;
        projectAbstract = projectAbstract1;
        initialProposal = initialProposal1;
        finalProposal = finalProposal1;
        liveLink = liveLink1;
    }

    
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    public String getUserName() {
      userName = loginBean.getUserName();
        return userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        StudentProfileBean.name = name;
    }

    public DefaultStreamedContent getDownloadFileProposal() {
        return downloadFileProposal;
    }

    public void setDownloadFileProposal(DefaultStreamedContent downloadFileProposal) {
        this.downloadFileProposal = downloadFileProposal;
    }

    public DefaultStreamedContent getDownloadFinalProposal() {
        return downloadFinalProposal;
    }

    public void setDownloadFinalProposal(DefaultStreamedContent downloadFinalProposal) {
        this.downloadFinalProposal = downloadFinalProposal;
    }

    
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        StudentProfileBean.projectName = projectName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        StudentProfileBean.keywords = keywords;
    }

    public String getProjectAbstract() {
        return projectAbstract;
    }

    public void setProjectAbstract(String projectAbstract) {
        StudentProfileBean.projectAbstract = projectAbstract;
    }

    public UploadedFile getInitialProposal() {
        return initialProposal;
    }

    public void setInitialProposal(UploadedFile initialProposal) {
        this.initialProposal = initialProposal;
    }

    public UploadedFile getFinalProposal() {
        return finalProposal;
    }

    public void setFinalProposal(UploadedFile finalProposal) {
        this.finalProposal = finalProposal;
    }

    public String getLiveLink() {
        return liveLink;
    }

    public void setLiveLink(String liveLink) {
        StudentProfileBean.liveLink = liveLink;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        StudentProfileBean.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        StudentProfileBean.contactNumber = contactNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        StudentProfileBean.course = course;
    }

    public String getSemesterCompleted() {
        return semesterCompleted;
    }

    public void setSemesterCompleted(String semesterCompleted) {
        StudentProfileBean.semesterCompleted = semesterCompleted;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        StudentProfileBean.major = major;
    }

}
