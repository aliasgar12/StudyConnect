package org.alias.studyconnect.services;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.alias.studyconnect.model.College;
import org.alias.studyconnect.model.Department;
import org.alias.studyconnect.model.Subject;
import org.alias.studyconnect.model.UserDetails;


public class SubjectService {
	
	private EntityManager em ;
	
	
	public Set<Subject> mySubjects(int id){
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		UserDetails user = em.find(UserDetails.class, id);
		user.getSubjectList();
		Set<Subject> mySubjects = (Set<Subject>) user.getSubjectList();
		em.getTransaction().commit();
		em.close();
		return mySubjects;
		
		// what jackson does in above situation is that it starts to eagerly fetch all the child from the
		// every subject in the subject list if the json ignore annotation is not mentioned.
	}


	public void addSubject(int id , Subject subject) {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		UserDetails user = em.find(UserDetails.class, id);
		user.getSubjectList().add(subject);
		em.getTransaction().commit();
		em.close();
	}


	public Set<Subject> delSubject(int id, Subject subject) {
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		UserDetails user = em.find(UserDetails.class, id);
		Subject sub = em.find(Subject.class, subject.getSubjectCRN());
		user.getSubjectList().remove(sub);
		em.getTransaction().commit();
		em.close();
		return user.getSubjectList(); 
		
	}
	
	public List<Subject>getAllSubjects(){
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from Subject");
		@SuppressWarnings("unchecked")
		List<Subject> subList = (List<Subject>)query.getResultList();
		em.getTransaction().commit();
		em.close();
		return subList;	
	}
	
	public List<Subject> getSubjectByCollege(int id){
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.find(College.class, id);
		Query query = em.createQuery("from Subject as sub where sub.college.collegeID = ?");
		query.setParameter(0 , new Integer(id));
		@SuppressWarnings("unchecked")
		List<Subject> subjects = (List<Subject>) query.getResultList();
		em.getTransaction().commit();
		em.close();
		return subjects;
	}
	
	public List<Subject> getSubjectsByCollegeDept(int colId, int deptId){
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.find(College.class, colId);
		em.find(Department.class, deptId);
		Query query = em.createQuery("from Subject as sub where sub.college.collegeID = ? and "
									+ "sub.dept.deptId = ?");
		query.setParameter(0 , new Integer(colId));
		query.setParameter(1 , new Integer(deptId));
		@SuppressWarnings("unchecked")
		List<Subject> subjects = (List<Subject>) query.getResultList();
		em.getTransaction().commit();
		em.close();
		return subjects;
	}
	
	public Subject getSubjectsByCRN(int CRN){
		em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		Subject subject = em.find(Subject.class, CRN);
		em.getTransaction().commit();
		em.close();
		return subject;
	}
	
}
