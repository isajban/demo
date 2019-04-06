package org.demo.pracovisko.procesov.vs.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.demo.pracovisko.procesov.vs.domain.Course;
import org.demo.pracovisko.procesov.vs.domain.Student;

public class StudentServiceTest {
	
	Logger log = Logger.getLogger(getClass().getName());
			
	EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("university-mysql");
	}

	@Test
	public void persistStudent() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Student s = new Student();
		s.setFirstName("Peter");
		s.setLastName("Doe");

		StudentService ss = new StudentService(em);
		ss.saveStudent(s);

		em.getTransaction().commit();
		em.close();

		Assert.assertNotNull(s.getId());
	}

	@Test
	public void updateStudent() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// persist a new Student
		Student s = createStudent(em);

		// Load and update the Student
		//StudentService ss = new StudentService(em);
		s.setFirstName("Martin");

		em.getTransaction().commit();
		em.close();

		// Validate update
		s = getStudent(s.getId());
		Assert.assertEquals("Martin", s.getFirstName());
	}

	@Test
	public void removeStudent() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// persist a new Student
		Student s = createStudent(em);

		// Load and update the Student
		StudentService ss = new StudentService(em);
		ss.removeStudent(s);

		em.getTransaction().commit();
		em.close();

		// Validate update
		s = getStudent(s.getId());
		Assert.assertNull(s);
	}
	
	@Test
	public void enrollStudentToCourse() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// persist a new Student
		Student s = createStudent(em);
		Course c = createCourse(em);
		

				
		log.info("....... START ......... 1 ..............");
		log.info(s.getId() + "  " + s.getFirstName() +"  " + s.getLastName());
		log.info(c.getId() + "  " + c.getName() + "  " + c.getCurriculum());
		
		CourseService cs = new CourseService(em);
		List<Course> courses = cs.findByName("Software Development 2019");
		Integer polozka = courses.size()-1;
		log.info("....... START ......... 2 ...............");
		log.info(".... Polozka: " + polozka);
		log.info(" ... Pocet kurzov: " + courses.size());
		log.info(courses.get(polozka).getId() + " ... " + courses.get(polozka).getName());
		
		StudentService ss = new StudentService(em);
		List<Student> students = ss.findByFirstNameAndLastName("Jane", "Doe");
		
		log.info("....... START ......... 3 ...............");
		c.getStudents().addAll(students);
		s.getCourses().addAll(courses);
		
		int numStudents = cs.countStudentsInCourse(c.getId());
		
		Assert.assertTrue(numStudents>0);

		em.getTransaction().commit();
		em.close();
		
	}

	private Course createCourse(EntityManager em) {
		
		Course c = new Course();
		c.setName("Software Development 2019");
		c.setStartDate(LocalDate.of(2019, 1, 1));
		c.setEndDate(LocalDate.of(2019, 12, 31));
		
		CourseService cs = new CourseService(em);
		cs.saveCourse(c);

		return c;
	}
	
	private Student createStudent(EntityManager em) {
		
		Student s = new Student();
		s.setFirstName("Jane");
		s.setLastName("Doe");

		StudentService ss = new StudentService(em);
		ss.saveStudent(s);
		
		return s;
	}

	private Student getStudent(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		StudentService ss = new StudentService(em);
		Student s = ss.findById(id);

		em.getTransaction().commit();
		em.close();

		return s;
	}
}
