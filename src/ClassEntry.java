/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class ClassEntry {
    private String semester;
    private String coursecode;
    private int seats;

    public ClassEntry(String semester, String coursecode, int seats) {
        this.semester = semester;
        this.coursecode = coursecode;
        this.seats = seats;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return coursecode;
    }

    public int getSeats() {
        return seats;
    }
    
    public String toString(){
        return String.format("Class " + coursecode);
    }
}
