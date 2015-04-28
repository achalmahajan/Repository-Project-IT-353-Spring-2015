/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
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
    
     private PieChartModel pieModel1;
    @PostConstruct
    public void init() {
        createPieModels();
    }
    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
    
    private void createPieModels() {
        createPieModel1();
    }
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        pieModel1.set("Project1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
