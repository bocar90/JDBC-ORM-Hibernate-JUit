package sba.sms.dao;

import sba.sms.models.Course;
import sba.sms.models.Student;

import java.util.List;

public interface StudentI {
	
    public List<Student> getAllStudents();
    
    public void createStudent(Student student);

    public Student getStudentByEmail(String email);

    public boolean validateStudent(String email, String password);

    public void registerStudentToCourse(String email, int courseId);

    public List<Course> getStudentCourses(String email);
}
