package org.elsysbg.ip.todo.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.elsysbg.ip.todo.entities.User;

@Singleton
public class UsersService {
	private final EntityManagerService entityManagerService;
	
	@Inject
	public UsersService(EntityManagerService entityManagerService) {
		this.entityManagerService = entityManagerService;
	}

	public User createUser(User user) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			
			return user;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	public List<User> getUsers() {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TypedQuery<User> query =
				em.createNamedQuery(User.QUERY_ALL, User.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public User getUser(long userId) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final User result = em.find(User.class, userId);
			if (result == null) {
				throw new IllegalArgumentException(
						"No user with id: " + userId);
			}
			return result;
		} finally {
			em.close();
		}
	}
	
}
