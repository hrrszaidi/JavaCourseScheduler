/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions;
    private static PreparedStatement getScheduledStudentsByClass;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static ResultSet resultSet;
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester){
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classDescriptionList = new ArrayList<ClassDescription>();
        try{
            getAllClassDescriptions = connection.prepareStatement("select c.coursecode, c.description, cl.seats from app.course c join app.class cl on c.coursecode = cl.coursecode where semester = ?");
            getAllClassDescriptions.setString(1,semester);
            resultSet = getAllClassDescriptions.executeQuery();
            
            while(resultSet.next()){
                classDescriptionList.add(new ClassDescription(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return classDescriptionList;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> schedulestudentlist = new ArrayList<StudentEntry>();
        try{
            getScheduledStudentsByClass = connection.prepareStatement("select st.studentid, st.firstname, st.lastname from app.student st join app.schedule sc on st.studentid = sc.studentid where semester = ? and courseCode = ? and status = ?");
            getScheduledStudentsByClass.setString(1,semester);
            getScheduledStudentsByClass.setString(2,courseCode);
            getScheduledStudentsByClass.setString(3,"S");
            resultSet = getScheduledStudentsByClass.executeQuery();
	    while(resultSet.next()){
		schedulestudentlist.add(new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
	    }
            
        }catch(SQLException sqlException){
	    sqlException.printStackTrace();
	}
	
	return schedulestudentlist;
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> waitlistedstudentlist = new ArrayList<StudentEntry>();
        try{
            getScheduledStudentsByClass = connection.prepareStatement("select st.studentid, st.firstname, st.lastname from app.student st join app.schedule sc on st.studentid = sc.studentid where semester = ? and courseCode = ? and status = ?");
            getScheduledStudentsByClass.setString(1,semester);
            getScheduledStudentsByClass.setString(2,courseCode);
            getScheduledStudentsByClass.setString(3,"W");
            resultSet = getScheduledStudentsByClass.executeQuery();
	    while(resultSet.next()){
		waitlistedstudentlist.add(new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
	    }
            
        }catch(SQLException sqlException){
	    sqlException.printStackTrace();
	}
	
	return waitlistedstudentlist;
    }
}
