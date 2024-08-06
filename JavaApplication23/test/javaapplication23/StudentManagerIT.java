package javaapplication23;

import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudentManagerIT {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream)); // Redirect stdout to capture output
        StudentManager.preloadStudents(); // Reset student data before each test
    }

    @After
    public void tearDown() {
        System.setOut(originalOut); // Restore stdout
    }

    @Test
    public void testRegisterStudent() {
        String input = "Alice\nalice@gmail.com\n9.0\n123 Main St\n20\nVietnamese\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        StudentManager.registerStudent();
        
        List<Student> students = StudentManager.getStudentCollection();
        assertEquals(5, students.size()); // Verify the number of students
        assertEquals("Alice", students.get(4).getName());
    }

    @Test
    public void testModifyStudentDetails() {
        String input = "doantung@gmail.com\nBob\n8.5\n456 Elm St\n21\nInternational\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        StudentManager.modifyStudentDetails();
        
        Student modifiedStudent = StudentManager.getStudentCollection().stream()
            .filter(s -> s.getEmail().equals("doantung@gmail.com"))
            .findFirst()
            .orElse(null);
        
        assertNotNull(modifiedStudent);
        assertEquals("Bob", modifiedStudent.getName());
        assertEquals(8.5, modifiedStudent.getScore(), 0.01);
        assertEquals("456 Elm St", modifiedStudent.getAddress());
        assertEquals(21, modifiedStudent.getAge());
        assertEquals("International", modifiedStudent.getGradingScale());
    }

    @Test
    public void testRemoveStudentRecord() {
        String input = "doantung@gmail.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        StudentManager.removeStudentRecord();
        
        List<Student> students = StudentManager.getStudentCollection();
        assertEquals(3, students.size()); // Verify the number of students
        assertNull(students.stream().filter(s -> s.getEmail().equals("doantung@gmail.com")).findFirst().orElse(null));
    }

    @Test
    public void testSearchStudentByEmail() {
        String input = "doantung@gmail.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        StudentManager.searchStudentByEmail();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Doan Tung"));
    }

    @Test
    public void testListAllStudents() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        StudentManager.listAllStudents();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Doan Tung"));
        assertTrue(output.contains("Junning"));
        assertTrue(output.contains("Quinn"));
        assertTrue(output.contains("Mai Linh"));
        
    }

    @Test
    public void testArrangeStudentsByScore() {
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        StudentManager.arrangeStudentsByScore();
        
        List<Student> students = StudentManager.getStudentCollection();
        assertEquals("Doan Tung", students.get(0).getName());
        assertEquals("Junning", students.get(1).getName());
        assertEquals("Quinn", students.get(2).getName());
        assertEquals("Linh", students.get(3).getName());
    }

    @Test
    public void testEvaluateStudentScores() {
        String input = "Doan Tung\nMai Linh\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        StudentManager.evaluateStudentScores("Doan Tung", "Mai Linh");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Doan Tung's score:"));
        assertTrue(output.contains("Mai Linh's score:"));
    }
}
