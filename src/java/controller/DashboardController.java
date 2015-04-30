/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DashboardDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import model.DashboardBean;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author it3530123
 */
@Named(value = "dashboardController")
@ManagedBean
@SessionScoped
public class DashboardController implements Serializable {

    /**
     * Creates a new instance of DashboardController
     */
    public DashboardController() {
    }

    @PostConstruct
    public void init() {
        createPieModels();
    }

    private List<DashboardBean> theDashboardBean;
    private List<DashboardBean> theDashboardBean2;

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public List<DashboardBean> getTheDashboardBean() {
        DashboardDAO theDashBoardDAO = new DashboardDAO();
        this.theDashboardBean = theDashBoardDAO.findAll();
        return theDashboardBean;
    }

    public void setTheDashboardBean(List<DashboardBean> theDashboardBean) {
        this.theDashboardBean = theDashboardBean;
    }

    public List<DashboardBean> getTheDashboardBean2() {
        DashboardDAO theDashBoardDAO = new DashboardDAO();
        this.theDashboardBean2 = theDashBoardDAO.findAll2();
        return theDashboardBean2;
    }

    public void setTheDashboardBean2(List<DashboardBean> theDashboardBean2) {
        this.theDashboardBean2 = theDashboardBean2;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        for (int i = 0; i < 5; i++) {
            int j = Integer.parseInt(getTheDashboardBean().get(i).getViewNumber());
            pieModel1.set(getTheDashboardBean().get(i).getUserName(), j);
        }
        pieModel1.setTitle("Most Viewed Projects");
        pieModel1.setLegendPosition("w");
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();
        for (int i = 0; i < 5; i++) {
            int j = Integer.parseInt(getTheDashboardBean2().get(i).getDownloadNumber());
            pieModel2.set(getTheDashboardBean2().get(i).getUserName(), j);
        }
        pieModel2.setTitle("Most Downloaded Projects");
        pieModel2.setLegendPosition("w");
    }

}
