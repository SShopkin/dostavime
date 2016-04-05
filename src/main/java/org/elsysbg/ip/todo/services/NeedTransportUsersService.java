package org.elsysbg.ip.todo.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.elsysbg.ip.todo.entities.NeedTransportUser;

@Singleton
public class NeedTransportUsersService {
	private final EntityManagerService entityManagerService;
	private final AuthenticationService authenticationService;
	
	@Inject
	public NeedTransportUsersService(EntityManagerService entityManagerService,
		AuthenticationService authenticationService) {
		this.entityManagerService = entityManagerService;
		this.authenticationService = authenticationService;
	}

	public NeedTransportUser createMember(NeedTransportUser needTransportUser) {
		needTransportUser.setPassword(authenticationService.encryptPassword(needTransportUser.getPassword()));
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(needTransportUser);
			em.getTransaction().commit();
			
			return needTransportUser;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	public List<NeedTransportUser> getMembers() {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TypedQuery<NeedTransportUser> query =
				em.createNamedQuery(NeedTransportUser.QUERY_ALL, NeedTransportUser.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public NeedTransportUser getMember(long needTransportUserId) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final NeedTransportUser result = em.find(NeedTransportUser.class, needTransportUserId);
			if (result == null) {
				throw new IllegalArgumentException(
						"No needTransportUser with id: " + needTransportUserId);
			}
			return result;
		} finally {
			em.close();
		}
	}

	public NeedTransportUser getMemberByUsername(String username) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TypedQuery<NeedTransportUser> query =
					em.createNamedQuery(NeedTransportUser.QUERY_BY_USERNAME, NeedTransportUser.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
	
}
