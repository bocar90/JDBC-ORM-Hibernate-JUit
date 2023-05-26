package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
class StudentServiceTest {

    static StudentService studentService;
    static CourseService courseService;

    @BeforeAll
    static void beforeAll() {
        studentService = new StudentService();
        courseService = new CourseService();
        //CommandLine.addData();
    }

    @Test
    void getAllStudents() {

        List<Student> expected = new ArrayList<>(Arrays.asList(
                new Student("reema@gmail.com", "reema brown", "password"),
                new Student("annette@gmail.com", "annette allen", "password"),
                new Student("anthony@gmail.com", "anthony gallegos", "password"),
                new Student("ariadna@gmail.com", "ariadna ramirez", "password"),
                new Student("bolaji@gmail.com", "bolaji saibu", "password")));

        Assertions.assertEquals(expected.size(), studentService.getAllStudents().size());

    }
    

	@Test
	void testGetCourseById() {

		Course actualCourse = new Course("Java","Phillip Witkin");
		Course retrievedCourse = courseService.getCourseById(1);
		System.out.print("retrieveCourse: " + retrievedCourse);

		Assertions.assertNotNull(retrievedCourse);

		Assertions.assertEquals(actualCourse.getInstructor(), retrievedCourse.getInstructor());
		
		Assertions.assertEquals(actualCourse.getName(), retrievedCourse.getName());

	}
    
}