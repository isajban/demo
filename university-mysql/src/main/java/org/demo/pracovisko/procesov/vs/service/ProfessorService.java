package org.demo.pracovisko.procesov.vs.service;

import javax.persistence.EntityManager;

import org.demo.pracovisko.procesov.vs.domain.Professor;

public class ProfessorService {

	private EntityManager em;
	
	public ProfessorService(EntityManager em) {
		this.em = em;
	}
	
	public Professor saveProfessor(Professor p) {
		em.persist(p);
		return p;
	}
	
	public Professor findById(Long id) {
		return em.find(Professor.class, id);
	}
	
	public void removeProfessor(Professor p) {
		em.remove(p);
	}
}
