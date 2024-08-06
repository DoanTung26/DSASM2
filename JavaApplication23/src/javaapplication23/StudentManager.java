package javaapplication23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private static final ArrayList<Student> studentCollection = new ArrayList<>();
    private static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        preloadStudents();

        boolean isRunning = true;
        while (isRunning) {
            displayOptions();
            int selection = userInput.nextInt();
            userInput.nextLine(); // Clear the newline

            switch (selection) {
                case 1 -> registerStudent();
                case 2 -> modifyStudentDetails();
                case 3 -> removeStudentRecord();
                case 4 -> searchStudentByEmail();
                case 5 -> listAllStudents();
                case 6 -> arrangeStudentsByScore(); // Existing Merge Sort
                case 7 -> arrangeStudentsByScoreSelectionSort(); // New Selection Sort
                case 8 -> evaluateStudentScores("Doan Tung", "Mai Linh");
                case 9 -> {
                    System.out.println("Shutting down...");
                    isRunning = false;
                }
                default -> System.out.println("Invalid selection! Please select a valid option.");
            }
        }
    }

    static void preloadStudents() {
        studentCollection.add(new Student("Doan Tung", "doantung@gmail.com", 9.2, "Gia Lam Ha Noi", 20, "Vietnamese"));
        studentCollection.add(new Student("Junning", "jun@gmail.com", 8.1, "Bac Tu Liem", 22, "Vietnamese"));
        studentCollection.add(new Student("Quinn", "quinn@gmail.com", 5.4, "Nam Tu Liem", 19, "Vietnamese"));
        studentCollection.add(new Student("Mai Linh", "lynh@gmail.com", 3.6, "Long Bien", 21, "Vietnamese"));
    }

    private static void displayOptions() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Register Student");
        System.out.println("2. Modify Student Details");
        System.out.println("3. Remove Student Record");
        System.out.println("4. Search Student by Email");
        System.out.println("5. List All Students");
        System.out.println("6. Arrange Students by Score (Collection Sort)");
        System.out.println("7. Arrange Students by Score (Selection Sort)");
        System.out.println("8. Evaluate Student Scores");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    public static void registerStudent() {
        System.out.print("Enter student name: ");
        String name = userInput.nextLine();
        System.out.print("Enter student email: ");
        String email = userInput.nextLine();
        if (!email.endsWith("@gmail.com")) {
            System.out.println("Invalid email format! Email must end with @gmail.com.");
            return;
        }
        System.out.print("Enter student score: ");
        double score = userInput.nextDouble();
        if (score < 0) {
            System.out.println("Invalid score! Score cannot be negative.");
            userInput.nextLine(); // Clear the newline character
            return;
        }
        userInput.nextLine(); // Clear the newline character after reading the score
        System.out.print("Enter student address: ");
        String address = userInput.nextLine();
        System.out.print("Enter student age: ");
        int age = userInput.nextInt();
        userInput.nextLine(); // Clear the newline character after reading the age
        System.out.print("Enter grading scale: ");
        String gradingScale = userInput.nextLine();

        studentCollection.add(new Student(name, email, score, address, age, gradingScale));
        System.out.println("Student registered successfully.");
    }

    public static void modifyStudentDetails() {
        System.out.print("Enter the email of the student to modify: ");
        String email = userInput.nextLine();

        for (Student student : studentCollection) {
            if (student.getEmail().equals(email)) {
                System.out.print("Enter new name: ");
                student.setName(userInput.nextLine());
                System.out.print("Enter new score: ");
                double newScore = userInput.nextDouble();
                if (newScore < 0) {
                    System.out.println("Invalid score! Score cannot be negative.");
                    userInput.nextLine(); // Clear the newline character
                    return;
                }
                student.setScore(newScore);
                userInput.nextLine();
                System.out.print("Enter new address: ");
                student.setAddress(userInput.nextLine());
                System.out.print("Enter new age: ");
                student.setAge(userInput.nextInt());
                userInput.nextLine();
                System.out.print("Enter new grading scale: ");
                student.setGradingScale(userInput.nextLine());
                System.out.println("Student details updated successfully.");
                return;
            }
        }
        System.out.println("Student with the provided email not found.");
    }

    public static void removeStudentRecord() {
        System.out.print("Enter the email of the student to remove: ");
        String email = userInput.nextLine();

        Student toRemove = null;
        for (Student student : studentCollection) {
            if (student.getEmail().equals(email)) {
                toRemove = student;
                break;
            }
        }

        if (toRemove != null) {
            studentCollection.remove(toRemove);
            System.out.println("Student record removed successfully.");
        } else {
            System.out.println("Student with the provided email not found.");
        }
    }

    public static void searchStudentByEmail() {
        System.out.print("Enter the email of the student to search: ");
        String email = userInput.nextLine();

        for (Student student : studentCollection) {
            if (student.getEmail().equals(email)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with the provided email not found.");
    }

    public static void listAllStudents() {
        if (studentCollection.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : studentCollection) {
                System.out.println(student);
                System.out.println("Rank: " + assignStudentRank(student.getScore(), student.getGradingScale()));
            }
        }
    }

    public static void arrangeStudentsByScore() {
        Collections.sort(studentCollection, (s1, s2) -> Double.compare(s2.getScore(), s1.getScore())); // Descending order
        System.out.println("Students arranged by score using Merge Sort.");
        listAllStudents();
    }

    public static void arrangeStudentsByScoreSelectionSort() {
        selectionSortStudentsByScore();
        System.out.println("Students arranged by score using Selection Sort.");
        listAllStudents();
    }

    private static void selectionSortStudentsByScore() {
        int n = studentCollection.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (studentCollection.get(j).getScore() < studentCollection.get(minIndex).getScore()) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            Collections.swap(studentCollection, i, minIndex);
        }
    }

    public static void evaluateStudentScores(String firstName, String secondName) {
        Student firstStudent = null;
        Student secondStudent = null;

        for (Student student : studentCollection) {
            if (student.getName().equalsIgnoreCase(firstName)) {
                firstStudent = student;
            }
            if (student.getName().equalsIgnoreCase(secondName)) {
                secondStudent = student;
            }
        }

        if (firstStudent == null || secondStudent == null) {
            System.out.println("One or both students not found.");
        } else {
            compareScores(firstStudent, secondStudent);
        }
    }

    private static void compareScores(Student s1, Student s2) {
        if (s1.getScore() > s2.getScore()) {
            System.out.println(s1.getName() + " has a higher score than " + s2.getName() + ".");
        } else if (s1.getScore() < s2.getScore()) {
            System.out.println(s2.getName() + " has a higher score than " + s1.getName() + ".");
        } else {
            System.out.println(s1.getName() + " and " + s2.getName() + " have the same score.");
        }
        System.out.println(s1.getName() + "'s score: " + s1.getScore() + " (" + assignStudentRank(s1.getScore(), s1.getGradingScale()) + ")");
        System.out.println(s2.getName() + "'s score: " + s2.getScore() + " (" + assignStudentRank(s2.getScore(), s2.getGradingScale()) + ")");
    }

    private static String assignStudentRank(double score, String gradingScale) {
        // Example grading scales; adjust as needed
        if (gradingScale.equals("Vietnamese")) {
            if (score < 0) {
                return "Invalid score: Score cannot be negative.";
            } else if (score < 5.0) {
                return "Fail";
            } else if (score < 6.5) {
                return "Medium";
            } else if (score < 7.5) {
                return "Good";
            } else if (score < 9.0) {
                return "Very Good";
            } else if (score <= 10.0) {
                return "Excellent";
            } else {
                return "Invalid score: Score cannot be greater than 10.";
            }
        } else if (gradingScale.equals("International")) {
            // Example international grading scale
            if (score < 0) {
                return "Invalid score: Score cannot be negative.";
            } else if (score < 50) {
                return "Fail";
            } else if (score < 60) {
                return "Pass";
            } else if (score < 70) {
                return "Credit";
            } else if (score < 80) {
                return "Distinction";
            } else if (score <= 100) {
                return "High Distinction";
            } else {
                return "Invalid score: Score cannot be greater than 100.";
            }
        } else {
            return "Unknown grading scale.";
        }
    }

    public static List<Student> getStudentCollection() {
        return studentCollection; // Return the actual student collection
    }
}
