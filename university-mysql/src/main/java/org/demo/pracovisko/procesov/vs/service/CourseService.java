package org.demo.pracovisko.procesov.vs.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.demo.pracovisko.procesov.vs.domain.Course;

public class CourseService {
	
	private EntityManager em;
	
	public CourseService(EntityManager em) {
		this.em = em;
	}
	
	public Course saveCourse(Course c) {
		em.persist(c);
		return c;
	}

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public void removeCourse(Course c) {
		em.remove(c);
	}
	
	public List<Course> findByName(String name) {
		TypedQuery<Course> q = em.createQuery("SELECT c FROM Course c WHERE c.name =: name", Course.class);
		q.setParameter("name", name);
		return q.getResultList();
	}
	
	public Integer countStudentsInCourse(Long id) {
		Query q = em.createQuery("SELECT SIZE(c.students) FROM Course c WHERE c.id =: id");
		q.setParameter("id", id);
		return (Integer) q.getSingleResult();
	}
}
