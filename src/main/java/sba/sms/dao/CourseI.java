package sba.sms.dao;

import sba.sms.models.Course;
import java.util.List;

public interface CourseI {
	
    public void createCourse(Course course);
    
    public Course getCourseById(int courseId);
    
    public List<Course> getAllCourses();

}
