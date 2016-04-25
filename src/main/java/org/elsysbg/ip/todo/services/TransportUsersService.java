package org.elsysbg.ip.todo.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.elsysbg.ip.todo.entities.TransportUser;

@Singleton
public class TransportUsersService {
	private final EntityManagerService entityManagerService;
	
	@Inject
	public TransportUsersService(EntityManagerService entityManagerService) {
		this.entityManagerService = entityManagerService;
	}

	public TransportUser createTransportUser(TransportUser transportUser) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(transportUser);
			em.getTransaction().commit();
			
			return transportUser;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	public List<TransportUser> getTransportUsers() {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TypedQuery<TransportUser> query =
				em.createNamedQuery(TransportUser.QUERY_ALL, TransportUser.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public TransportUser getTransportUser(long transportUserId) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TransportUser result = em.find(TransportUser.class, transportUserId);
			if (result == null) {
				throw new IllegalArgumentException(
						"No transportUser with id: " + transportUserId);
			}
			return result;
		} finally {
			em.close();
		}
	}
	
}
