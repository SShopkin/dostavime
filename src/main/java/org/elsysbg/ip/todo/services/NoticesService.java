package org.elsysbg.ip.todo.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.elsysbg.ip.todo.entities.Notice;

@Singleton
public class NoticesService {
	private final EntityManagerService entityManagerService;
	
	@Inject
	public NoticesService(EntityManagerService entityManagerService) {
		this.entityManagerService = entityManagerService;
	}

	public Notice createNotice(Notice notice) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(notice);
			em.getTransaction().commit();
			
			return notice;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
	public List<Notice> getNotices() {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TypedQuery<Notice> query =
				em.createNamedQuery(Notice.QUERY_ALL, Notice.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	public Notice getNotice(long noticeId) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final Notice result = em.find(Notice.class, noticeId);
			if (result == null) {
				throw new IllegalArgumentException(
						"No notice with id: " + noticeId);
			}
			return result;
		} finally {
			em.close();
		}
	}
	public Notice updateNotice(Notice notice) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			final Notice result = em.merge(notice);
			em.getTransaction().commit();
			
			return result;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
	public void deleteNotice(long noticeId) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			final Notice notice = em.find(Notice.class, noticeId);
			if (notice == null) {
				throw new IllegalArgumentException(
						"No notice with id: " + noticeId);
			}
			em.remove(notice);
			
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	
}
