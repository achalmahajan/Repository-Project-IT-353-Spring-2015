/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author it3530123
 */
public class DashboardBean {
    
    
    private String userName;
    private String projectName;
    private String viewNumber;
    private String downloadNumber;
    

    public String getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(String downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(String viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public DashboardBean(){
        
    }

    public DashboardBean(String userName, String projectName, String viewNumber) {
        this.userName = userName;
        this.projectName = projectName;
        this.viewNumber = viewNumber;
    }

    
    
    
}
