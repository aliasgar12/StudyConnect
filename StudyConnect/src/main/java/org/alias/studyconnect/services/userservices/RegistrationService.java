package org.alias.studyconnect.services.userservices;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.alias.studyconnect.model.UserDetails;

public class RegistrationService {
	private EntityManager entityManager;
	
	public UserDetails registerUser(UserDetails user){
		entityManager = EntityUtil.getEntityManager();
 
		entityManager.getTransaction().begin();
 
		entityManager.persist(user);
 
		entityManager.getTransaction().commit();
		entityManager.close();
		return user;
	}
	
}
