/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication23;

/**
 *
 * @author Dell
 */
public class Student {
    private String name;
    private String email;
    private double score;
    private String address;
    private int age;
    private String gradingScale;

    public Student(String name, String email, double score, String address, int age, String gradingScale) {
        this.name = name;
        this.email = email;
        this.score = score;
        this.address = address;
        this.age = age;
        this.gradingScale = gradingScale;
    }

    // Getter and Setter methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGradingScale() { return gradingScale; }
    public void setGradingScale(String gradingScale) { this.gradingScale = gradingScale; }

    @Override
    public String toString() {
        return String.format("Name: %s\nEmail: %s\nScore: %.2f\nAddress: %s\nAge: %d\nGrading Scale: %s",
                name, email, score, address, age, gradingScale);
    }
}