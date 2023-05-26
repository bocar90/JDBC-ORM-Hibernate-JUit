package sba.sms.models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name = "student_courses", 
	joinColumns = @JoinColumn(name = "FK_student_email"),
	inverseJoinColumns = @JoinColumn(name = "courses_id"),
	inverseForeignKey = @ForeignKey(name = "Fk_courses_id"))
	private List<Course> courses;
	
	public Student() {
		
	}

	public Student(String email, String name, String password, List<Course> courses) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.courses = courses;
	}

	public Student(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [email=" + email + ", name=" + name + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courses, email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courses, other.courses) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	};
	
		
}
