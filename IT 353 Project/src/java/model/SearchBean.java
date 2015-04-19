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

public class SearchBean {

//    private  String firstName;
    private String userName;
    private String projectName;
    private String keywords;
    private String projectAbstract;
    
//     private String searchKeyword;
//
//    public String getSearchKeyword() {
//        return searchKeyword;
//    }
//
//    public void setSearchKeyword(String searchKeyword) {
//        this.searchKeyword = searchKeyword;
//    }
//     

    
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

    public SearchBean(){
        
    }


}
