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
import java.sql.Timestamp;

public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement dropScheduleByCourse;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry scheduleEntry){
        connection = DBConnection.getConnection();
        try{
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, coursecode, studentid, status, timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1,scheduleEntry.getSemester());
            addScheduleEntry.setString(2,scheduleEntry.getCourseCode());
            addScheduleEntry.setString(3,scheduleEntry.getStudentID());
            addScheduleEntry.setString(4,scheduleEntry.getStatus());
            addScheduleEntry.setTimestamp(5,scheduleEntry.getTimeStamp());
            addScheduleEntry.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduleEntryList = new ArrayList<ScheduleEntry>();
        try{
        getScheduleByStudent = connection.prepareStatement("select * from app.schedule where semester = ? and studentid = ?");
        getScheduleByStudent.setString(1,semester);
        getScheduleByStudent.setString(2,studentID);
        resultSet = getScheduleByStudent.executeQuery();
            
        while(resultSet.next()){
            scheduleEntryList.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
        }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
            return scheduleEntryList;
        }
        
    public static int getScheduleStudentCount(String semester, String coursecode){
        connection = DBConnection.getConnection();
        int scheduleStudentCount = 0;
        try{
        getScheduledStudentCount = connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and courseCode = ?");
        getScheduledStudentCount.setString(1,semester);
        getScheduledStudentCount.setString(2,coursecode);
        resultSet = getScheduledStudentCount.executeQuery();
            
        if(resultSet.next()){
            scheduleStudentCount = resultSet.getInt(1);
        }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
            return scheduleStudentCount;
        }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlist = new ArrayList<ScheduleEntry>();
        try{
        getWaitlistedStudentsByClass = connection.prepareStatement("select * from app.schedule where semester = ? and coursecode = ? and status = ? order by timestamp");
        getWaitlistedStudentsByClass.setString(1,semester);
        getWaitlistedStudentsByClass.setString(2,courseCode);
        getWaitlistedStudentsByClass.setString(3,"W");
        resultSet = getWaitlistedStudentsByClass.executeQuery();
            
        while(resultSet.next()){
            waitlist.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
        }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
            return waitlist;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and studentid = ? and coursecode = ?");
            dropStudentScheduleByCourse.setString(1,semester);
            dropStudentScheduleByCourse.setString(2,studentID);
            dropStudentScheduleByCourse.setString(3,courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    public static void updateScheduleEntry(ScheduleEntry schedule){
        connection = DBConnection.getConnection();
        try{
	    updateScheduleEntry = connection.prepareStatement("update app.schedule set status='S' where studentid = ? and courseCode = ? and semester = ?");
	    updateScheduleEntry.setString(1,schedule.getStudentID());
	    updateScheduleEntry.setString(2, schedule.getCourseCode());
	    updateScheduleEntry.setString(3, schedule.getSemester());
            updateScheduleEntry.executeUpdate();
	}catch(SQLException sqlException){
	    sqlException.printStackTrace();
	}
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
	try{
	    dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and courseCode = ?");
	    dropScheduleByCourse.setString(1, semester);
	    dropScheduleByCourse.setString(2, courseCode);
	    dropScheduleByCourse.executeUpdate();
	}catch(SQLException sqlException){
	    sqlException.printStackTrace();
	}
    }
}
