/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ApproveStudentsDAOImpl;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ApproveStudentsBean;

/**
 *
 * @author it3530123
 */
@ManagedBean
@SessionScoped
public class ApproveStudentsController {
    
    //private ApproveStudentsBean theApproveStudentBean;
    private ApproveStudentsDAOImpl theApproveStudentsDAOImpl;
    
    private List<ApproveStudentsBean> profiles;

    public ApproveStudentsController() {
    }
    public List<ApproveStudentsBean> getProfileBeans() {
        // get the profiles by interacting with ProfileDAO.
        ApproveStudentsDAOImpl aStudentDAO = new ApproveStudentsDAOImpl();    // Creating a new object each time.
        this.profiles = aStudentDAO.findAll();            // Doing anything with the object after this?
//        this.profiles = aProfileDAO.findByName("Billy Lim");            // Doing anything with the object after this?
        return profiles;
    }

    public void setProfileBeans(List<ApproveStudentsBean> profiles) {
        this.profiles = profiles;
    }
    
    public void getStudents(){
        //theApproveStudentBean = new ApproveStudentsBean();
        theApproveStudentsDAOImpl = new ApproveStudentsDAOImpl();
//       ArrayList returnedStudentCollection = theApproveStudentsDAOImpl.findAll();
       for(int i =0; i<profiles.size();i++){
           profiles.get(i).getFirstName();
//           System.out.println("chala");
         //  System.out.println(returnedStudentCollection.get(i).toString());
       }
    }
    
    
}
