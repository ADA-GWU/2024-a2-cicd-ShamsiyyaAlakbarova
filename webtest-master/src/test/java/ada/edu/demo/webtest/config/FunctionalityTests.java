
package ada.edu.demo.webtest.config;

import ada.edu.demo.webtest.entity.Student;
import ada.edu.demo.webtest.repository.StudentRepository;
import ada.edu.demo.webtest.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FunctionalityTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("Test finding a student by ID")
    public void testStudentSearchById() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(new Student()));
        Student result = studentService.getStudentById(1);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Search by first or last name")
    public void testStudentSearch() {
        Student s1 = new Student(1,"Jamal","Hasanov","a@b.com",new Date(),null,null);
        Student s2 = new Student(2,"Aliya","Mammadova","a@b.com",new Date(),null,null);
        Student s3 = new Student(3,"Kamran","Aliyev","a@b.com",new Date(),null,null);

        List<Student> stList = List.of(s1,s2,s3);

        when(studentRepository.findAll()).thenReturn(stList);
        List<Student> students = studentService.getStudentByEitherName("Jamal","Aliyev");
        System.out.printf("Found students: "+students.size());
        assertEquals(2, students.size());
    }

    @Test
    @DisplayName("Ensure getStudentByName accurately retrieves students by name")
    void testGetStudentByName() {
        // Arrange
        Student shamsNajaf = new Student();
        shamsNajaf.setStudentId(1);
        shamsNajaf.setFirstName("Shams");
        shamsNajaf.setLastName("Najaf");

        Student shamsiyyaNajaf = new Student();
        shamsiyyaNajaf.setStudentId(2);
        shamsiyyaNajaf.setFirstName("Shamsiyya");
        shamsiyyaNajaf.setLastName("Najaf");

        when(studentRepository.findByNameCase("%Najaf%")).thenReturn(Arrays.asList(shamsNajaf, shamsiyyaNajaf));

        // Act
        Iterable<Student> students = studentService.getStudentByName("Najaf");

        // Assert
        assertTrue(((List<Student>) students).contains(shamsNajaf));
        assertTrue(((List<Student>) students).contains(shamsiyyaNajaf));
    }
}

