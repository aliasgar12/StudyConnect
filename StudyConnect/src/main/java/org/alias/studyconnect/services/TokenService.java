package org.alias.studyconnect.services;

import javax.persistence.EntityManager;
import org.alias.studyconnect.model.UserDetails;

public class TokenService {

	private EntityManager em;

	public int setToken(UserDetails user) {
		// TODO Auto-generated method stub
		try {
			String token = user.getApp_token();
			em = EntityUtil.getEntityManager();
			em.getTransaction().begin();
			UserDetails userTemp = em.find(UserDetails.class, user.getUserId());
			userTemp.setApp_token(token);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int deleteToken(UserDetails user) {
		// TODO Auto-generated method stub
		try {
			em = EntityUtil.getEntityManager();
			em.getTransaction().begin();
			UserDetails userTemp = em.find(UserDetails.class, user.getUserId());
			userTemp.setApp_token(null);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

}
