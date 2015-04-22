/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SearchDAO;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.SearchBean;

/**
 *
 * @author it3530123
 */
@ManagedBean
@SessionScoped
public class SearchController implements Serializable {

    private List<SearchBean> theSearchBean;
    private String searchKeyword;

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword1) {
        this.searchKeyword = searchKeyword1;
    }

    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {

    }

    public List<SearchBean> getTheSearchBean() {
        SearchDAO aSearchDAO = new SearchDAO();
        this.theSearchBean = aSearchDAO.findAll(searchKeyword);
        return theSearchBean;
    }

    public void setTheSearchBean(List<SearchBean> theSearchBean) {
        this.theSearchBean = theSearchBean;
    }

}
