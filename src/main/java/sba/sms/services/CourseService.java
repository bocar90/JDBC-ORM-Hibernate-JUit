package sba.sms.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;

public class CourseService implements CourseI {

	@Override
	public void createCourse(Course course) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();

		try {
			session.persist(course);
			t.commit();

		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			if (factory != null)
				factory.close();
		}
		
	}

	@Override
	public Course getCourseById(int courseId) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		
		Course course = new Course();
		
		try {
			// retrieve course by its ID
			course = session.get(Course.class, courseId);

			t.commit();
		} catch (Exception e) {
			if (t != null) 
				t.rollback();			
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			if (factory != null)
				factory.close();
		}

		return course;
	}

	@Override
	public List<Course> getAllCourses() {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		
		List<Course> coursesList = new ArrayList<>();

		try {
			coursesList = session.createQuery("FROM Course", Course.class).list();
			
			t.commit();
			// Check for duplicates and add only unique activities to the list
	        for (Course course : coursesList) {
	            if (!coursesList.contains(course))
	            	coursesList.add(course);
	        }

		} catch (Exception e) {
			if(t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			if (factory != null)
				factory.close();
		}

		return coursesList;
	}

}
