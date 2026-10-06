/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class StudentEntry {
    private String studentID;
    private String firstname;
    private String lastname;

    public StudentEntry(String studentID, String firstname, String lastname) {
        this.studentID = studentID;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }
    
    
    public String toString(){
        return String.format("%s, %s %s", lastname,firstname,studentID);
    }
    
}
