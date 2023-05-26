package sba.sms.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;


public class StudentService implements StudentI{

	@Override
	public List<Student> getAllStudents() {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			studentList = session.createQuery("FROM Student", Student.class).list();
			t.commit();
			
		} catch(Exception e) {
			if(t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			if(factory != null)
				factory.close();
			if(session != null)
				session.close();
		}
		return studentList;
	}

	@Override
	public void createStudent(Student student) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
        Session session = factory.openSession();
        
        Transaction t = session.beginTransaction();
        
        try {
            session.persist(student);
            
            t.commit();
        } catch (Exception e) {
            if (t != null && t.isActive()) 
                t.rollback();
            e.printStackTrace();
        } finally {
        	if(factory != null)
				factory.close();
			if(session != null)
				session.close();
        }
		
	}

	@Override
	public Student getStudentByEmail(String email) {
		
		SessionFactory factory= new Configuration().configure().buildSessionFactory();
		
        Session session= factory.openSession();
        
        Transaction t= session.beginTransaction();
        
        Student studentByEmail = new Student();
        
        try{
        	studentByEmail = session.createQuery("From Student where email = :email", Student.class)
                    .setParameter("email", email).uniqueResult();
        	
            t.commit();
            
        }catch (Exception e){
            if (t != null)
                t.rollback();
            e.printStackTrace();
        } finally {
        	if(factory != null)
				factory.close();
			if(session != null)
				session.close();
        }
        return studentByEmail;
	}

	@Override
	public boolean validateStudent(String email, String password) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
        Session session = factory.openSession();
        
        Transaction t = session.beginTransaction();
        
         try {
             Student student = new Student();
             student = session.createQuery("From Student where email = :email and password = :password", Student.class)
                     .setParameter("email", email).setParameter("password", password).uniqueResult();
             
             if(student != null)
                 return true;
             
             t.commit();
             
         } catch (Exception e) {
             if (t != null)
                 t.rollback();
             e.printStackTrace();
         } finally {
        	 if(factory != null)
 				factory.close();
 			 if(session != null)
 				session.close();
         }
        return false;
	}

	@Override
	public void registerStudentToCourse(String email, int courseId) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
        Session session = factory.openSession();
        
        Transaction t = session.beginTransaction();
        
        try {

            Student student = session.createQuery("FROM Student WHERE email = :email", Student.class)
                    .setParameter("email", email).uniqueResult();
 
            Course course = session.get(Course.class, courseId);
            
            if (student != null && course != null) {
                if (!student.getCourses().contains(course))
                    student.getCourses().add(course);   
            }

            t.commit();
            
        } catch (Exception e) {
            if (t != null)
                t.rollback();
            e.printStackTrace();
        } finally {
        	if(factory != null)
 				factory.close();
 			if(session != null)
 				session.close();
        }
		
	}

	@Override
	public List<Course> getStudentCourses(String email) {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
        Session session = factory.openSession();
        
        Transaction t = session.beginTransaction();
        
        List<Course> coursesList = new ArrayList<>();
        
        try {
            // Perform the database query to retrieve the Volunteer activities using a native query
            String query = "SELECT c.* FROM Course c " +
					"INNER JOIN student_courses sc ON c.id = sc.courses_id " +
                    "INNER JOIN Student s ON sc.FK_student_email = s.email " +
                    "WHERE s.email = :email";

            coursesList = session.createNativeQuery(query, Course.class)
                    .setParameter("email", email)
                    .list();
            
            t.commit();
            
        } catch (Exception e) {
            if (t != null)
                t.rollback();   
            e.printStackTrace();
        } finally {
        	if(factory != null)
 				factory.close();
 			if(session != null)
 				session.close();
        }
        return coursesList;
	}

}
