
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
        Student s1 = new Student(1, "Jamal", "Hasanov", "a@b.com", new Date(), null, null);
        Student s2 = new Student(2, "Aliya", "Mammadova", "a@b.com", new Date(), null, null);
        Student s3 = new Student(3, "Kamran", "Aliyev", "a@b.com", new Date(), null, null);

        List<Student> stList = List.of(s1, s2, s3);

        when(studentRepository.findAll()).thenReturn(stList);
        List<Student> students = studentService.getStudentByEitherName("Jamal", "Aliyev");
        System.out.printf("Found students: " + students.size());
        assertEquals(2, students.size());
    }
    @Test
    @DisplayName("Get student by name is accurate")
    void testGetStudentByName() {

        Student shamsNajaf = new Student();
        shamsNajaf.setStudentId(1);
        shamsNajaf.setFirstName("Shams");
        shamsNajaf.setLastName("Najaf");


        Student shamsAlakbar = new Student();
        shamsAlakbar.setStudentId(2);
        shamsAlakbar.setFirstName("Shams");
        shamsAlakbar.setLastName("Alakbar");

       when(studentRepository.findByNameCase("%Shams%")).thenReturn(Arrays.asList(shamsNajaf, shamsAlakbar));

       Iterable<Student> students = studentService.getStudentByName("Shams");

       assertTrue(((List<Student>) students). contains(shamsNajaf));
       assertTrue(((List<Student>) students). contains(shamsAlakbar));
    }

}

