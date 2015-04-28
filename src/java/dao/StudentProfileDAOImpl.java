/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.StudentProfileBean;
import model.ViewStudentDocuments;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author it3530123
 */
public class StudentProfileDAOImpl {

    String downloadNumber;
    String viewNumber;

    public int uploadDocument(StudentProfileBean aModel) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String insertString;
                String combinedString = aModel.getProjectName() + " " + aModel.getKeywords() + " " + aModel.getProjectAbstract();
                //Statement stmt = DBConn.createStatement();
                //need to change the name of the table to signupapproval
                insertString = "update studentproject set projectName =?, keywords =?, abstract=?, projectProposal=?, proposalName=?, finalProposal=?, finalProposalName=?, liveLink=?, viewNumber=?, downloadedNumber=?, details=? WHERE userId = '" + aModel.getName() + "'";

                InputStream fileInStream = aModel.getInitialProposal().getInputstream();
                InputStream fileInStream1 = aModel.getFinalProposal().getInputstream();
                PreparedStatement stmt = DBConn.prepareStatement(insertString);

//                stmt.setString(1, aModel.getName());
                stmt.setString(1, aModel.getProjectName());
                stmt.setString(2, aModel.getKeywords());
                stmt.setString(3, aModel.getProjectAbstract());
                stmt.setBinaryStream(4, fileInStream, aModel.getInitialProposal().getSize());
                stmt.setString(5, aModel.getInitialProposal().getFileName());
                stmt.setBinaryStream(6, fileInStream1, aModel.getFinalProposal().getSize());
                stmt.setString(7, aModel.getFinalProposal().getFileName());
                stmt.setString(8, aModel.getLiveLink());
                stmt.setString(9, "0");
                stmt.setString(10, "0");
                stmt.setString(11, combinedString);

                rowCount = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    public int uploadProfile(StudentProfileBean aModel) throws IOException {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String insertString;
                insertString = "UPDATE student SET gender=? , phone=?, course=?, major=?, semcompleted=? WHERE userId = '" + aModel.getName() + "'";
                PreparedStatement stmt = DBConn.prepareStatement(insertString);

                stmt.setString(1, aModel.getGender());
                stmt.setString(2, aModel.getContactNumber());
                stmt.setString(3, aModel.getCourse());
                stmt.setString(4, aModel.getMajor());
                stmt.setString(5, aModel.getSemesterCompleted());

                rowCount = stmt.executeUpdate();

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    public DefaultStreamedContent downloadFileFromDB(StudentProfileBean aModel) {
        DefaultStreamedContent resumeDownload = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String retrieveString;
                retrieveString = "SELECT * FROM StudentProject where userId = '" + aModel.getName() + "'";
                Statement stmt = DBConn.createStatement();
                byte[] resume;
//                DefaultStreamedContent resumeDownload = null;
                ResultSet rs = stmt.executeQuery(retrieveString);

                while (rs.next()) {
                    String proposalName = rs.getString("ProposalName");
                    resume = rs.getBytes("PROJECTPROPOSAL");
//                    aModel.setDownloadFile(this.binaryToDefaultStreamedContent(resume, "pdf/docx"));
                    resumeDownload = new DefaultStreamedContent(new ByteArrayInputStream(resume), "application/docx", proposalName);
                    aModel.setDownloadFileProposal(resumeDownload);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return resumeDownload;
    }

    public DefaultStreamedContent downloadFinalFromDB(StudentProfileBean aModel) {
        DefaultStreamedContent finalDownload = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String retrieveString;
                retrieveString = "SELECT * FROM StudentProject where userId = '" + aModel.getName() + "'";
                Statement stmt = DBConn.createStatement();
                byte[] finalProposal;
//                DefaultStreamedContent resumeDownload = null;
                ResultSet rs = stmt.executeQuery(retrieveString);

                while (rs.next()) {
                    String finalProposalName = rs.getString("FinalProposalName");
                    finalProposal = rs.getBytes("FINALPROPOSAL");
//                    aModel.setDownloadFile(this.binaryToDefaultStreamedContent(resume, "pdf/docx"));
                    finalDownload = new DefaultStreamedContent(new ByteArrayInputStream(finalProposal), "application/docx", finalProposalName);
                    aModel.setDownloadFinalProposal(finalDownload);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return finalDownload;
    }

    public StudentProfileBean fetchStudentProfile(String userName) {
        StudentProfileBean theModel = new StudentProfileBean();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String fetchString;
                fetchString = "SELECT * FROM Student where userId = '" + userName + "'";
                Statement stmt = DBConn.createStatement();
                ResultSet rs = stmt.executeQuery(fetchString);

                while (rs.next()) {
                    theModel.setGender(rs.getString("gender"));
                    theModel.setContactNumber(rs.getString("phone"));
                    theModel.setCourse(rs.getString("course"));
                    theModel.setMajor(rs.getString("major"));
                    theModel.setSemesterCompleted(rs.getString("semcompleted"));
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return theModel;

    }

    public StudentProfileBean fetchStudentDocuments(String userName) {
        StudentProfileBean theModel = new StudentProfileBean();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String fetchDocumentStr;
                fetchDocumentStr = "SELECT * FROM StudentProject where userId = '" + userName + "'";
                Statement stmt = DBConn.createStatement();
                ResultSet rs = stmt.executeQuery(fetchDocumentStr);
                byte[] initialProposal;
                byte[] finalProposal;

                while (rs.next()) {
                    theModel.setProjectName(rs.getString("projectName"));
                    theModel.setKeywords(rs.getString("keywords"));
                    theModel.setProjectAbstract(rs.getString("abstract"));
                    initialProposal = rs.getBytes("projectProposal");
                    if (initialProposal != null) {
                        theModel.setDownloadFileProposal(this.binaryToDefaultStreamedContent(initialProposal, "docx"));
                    }
                    finalProposal = rs.getBytes("finalProposal");
                    if (finalProposal != null) {
                        theModel.setDownloadFinalProposal(this.binaryToDefaultStreamedContent(finalProposal, "docx"));
                    }
                    theModel.setLiveLink(rs.getString("liveLink"));
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return theModel;
    }

    public DefaultStreamedContent binaryToDefaultStreamedContent(byte[] binaryFile, String ext) {
        DefaultStreamedContent fileToShow = new DefaultStreamedContent(new ByteArrayInputStream(binaryFile), ext);
        return fileToShow;
    }

    public ArrayList findAllStudentDocuments() {

        String sqlStr = "SELECT * FROM StudentProject";
        ArrayList aStudentsCollection = selectStudentsFromDB(sqlStr);
        return aStudentsCollection;

    }

    private ArrayList selectStudentsFromDB(String sqlStr) {
        ArrayList aStudentProjectCollection = new ArrayList();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");            // driver to connect to database - this is part of jdk
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";            // connection string, jdbc: protocol for db; derby is the db;
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            byte[] initialProposal;
            byte[] finalProposal;

            while (rs.next()) {
                ViewStudentDocuments viewModel = new ViewStudentDocuments();
                viewModel.setUserName(rs.getString("userId"));
                viewModel.setProjectName(rs.getString("projectName"));
                viewModel.setKeywords(rs.getString("keywords"));
                viewModel.setProjectAbstract(rs.getString("abstract"));
                initialProposal = rs.getBytes("projectProposal");
                if (initialProposal != null) {
                    viewModel.setDownloadFileProposal(this.binaryToDefaultStreamedContent(initialProposal, "docx"));
                }
                finalProposal = rs.getBytes("finalProposal");
                if (finalProposal != null) {
                    viewModel.setDownloadFinalProposal(this.binaryToDefaultStreamedContent(finalProposal, "docx"));
                }
                viewModel.setLiveLink(rs.getString("liveLink"));
                aStudentProjectCollection.add(viewModel);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aStudentProjectCollection;
    }

    public DefaultStreamedContent testDownloadFromDB(ViewStudentDocuments aModel) {
        DefaultStreamedContent resumeDownload = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String retrieveString;
                retrieveString = "SELECT * FROM StudentProject where userId = '" + aModel.getUserName() + "'";
                Statement stmt = DBConn.createStatement();
                byte[] resume;
                ResultSet rs = stmt.executeQuery(retrieveString);

                while (rs.next()) {
                    String proposalName = rs.getString("ProposalName");
                    resume = rs.getBytes("PROJECTPROPOSAL");
                    resumeDownload = new DefaultStreamedContent(new ByteArrayInputStream(resume), "application/docx", proposalName);
                    aModel.setDownloadFileProposal(resumeDownload);
                    String actualDownloadNumber = rs.getString("DownloadedNumber");
                    int i = Integer.parseInt(actualDownloadNumber);
                    downloadNumber = String.valueOf(i + 1);
                }

                String uploadNumber = "Update StudentProject set DownloadedNumber = '" + downloadNumber + "' where userId = '" + aModel.getUserName() + "'";
                int i = stmt.executeUpdate(uploadNumber);

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resumeDownload;
    }

    public DefaultStreamedContent testFinalDownloadFromDB(ViewStudentDocuments aModel) {
        DefaultStreamedContent finalDownload = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String retrieveString;
                retrieveString = "SELECT * FROM StudentProject where userId = '" + aModel.getUserName() + "'";
                Statement stmt = DBConn.createStatement();
                byte[] finalProposal;
                ResultSet rs = stmt.executeQuery(retrieveString);

                while (rs.next()) {
                    String finalProposalName = rs.getString("FinalProposalName");
                    finalProposal = rs.getBytes("FINALPROPOSAL");
                    finalDownload = new DefaultStreamedContent(new ByteArrayInputStream(finalProposal), "application/docx", finalProposalName);
                    aModel.setDownloadFinalProposal(finalDownload);
                    String actualDownloadNumber = rs.getString("DownloadedNumber");
                    int i = Integer.parseInt(actualDownloadNumber);
                    downloadNumber = String.valueOf(i + 1);
                }
                String uploadNumber = "Update StudentProject set DownloadedNumber = '" + downloadNumber + "' where userId = '" + aModel.getUserName() + "'";
                int i = stmt.executeUpdate(uploadNumber);

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return finalDownload;
    }

    public void viewIncrement(ViewStudentDocuments aModel) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/RepositoryDB";
            try (Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student")) {
                String retrieveString;
                retrieveString = "SELECT * FROM StudentProject where userId = '" + aModel.getUserName() + "'";
                Statement stmt = DBConn.createStatement();
                ResultSet rs = stmt.executeQuery(retrieveString);

                while (rs.next()) {
                    String actualViewNumber = rs.getString("ViewNumber");
                    int i = Integer.parseInt(actualViewNumber);
                    viewNumber = String.valueOf(i + 1);
                }
                String uploadNumber = "Update StudentProject set ViewNumber = '" + viewNumber + "' where userId = '" + aModel.getUserName() + "'";
                int i = stmt.executeUpdate(uploadNumber);
                rs.close();
                stmt.close();

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
