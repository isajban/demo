package org.demo.pracovisko.procesov.vs.service;

import javax.persistence.EntityManager;

import org.demo.pracovisko.procesov.vs.domain.Curriculum;

public class CurriculumService {

	private EntityManager em;
	
	public CurriculumService(EntityManager em) {
		this.em = em;
	}
	
	public Curriculum saveCurriculum(Curriculum c) {
		em.persist(c);
		return c;
	}
	
	public Curriculum findById(Long id) {
		return em.find(Curriculum.class, id);
	}
	
	public void removeCurriculum(Curriculum c) {
		em.remove(c);
	}
}
