/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author it3530123
 */
public class ViewStudentDocuments {
    
    private String userName;
    private String projectName;
    private String keywords;
    private String projectAbstract;
    DefaultStreamedContent downloadFileProposal;
    DefaultStreamedContent downloadFinalProposal;
    private String liveLink;
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getProjectAbstract() {
        return projectAbstract;
    }

    public void setProjectAbstract(String projectAbstract) {
        this.projectAbstract = projectAbstract;
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

    public String getLiveLink() {
        return liveLink;
    }

    public void setLiveLink(String liveLink) {
        this.liveLink = liveLink;
    }
    
}
