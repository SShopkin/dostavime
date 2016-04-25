package org.elsysbg.ip.todo.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.todo.entities.Notice;
import org.elsysbg.ip.todo.services.AuthenticationService;
import org.elsysbg.ip.todo.services.NoticesService;

@Path("/notice")
public class NoticeRest {
	private final NoticesService noticesService;
	private final AuthenticationService authenticationService;

	@Inject
	public NoticeRest(NoticesService noticesService,
			AuthenticationService authenticationService) {
		this.noticesService = noticesService;
		this.authenticationService = authenticationService;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Notice> getNotices() {
		return noticesService.getNotices();
	}
	
	@GET
	@Path("/{noticeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Notice getNotice(@PathParam("noticeId") long noticeId) {
		return noticesService.getNotice(noticeId);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Notice createNotice(Notice notice) {
		notice.setAuthor(authenticationService.getCurrentlyLoggedInUser());
		return noticesService.createNotice(notice);
	}
	
	@DELETE
	@Path("/{noticeId}")
	public void deleteNotice(@PathParam("noticeId") long noticeId) {
		noticesService.deleteNotice(noticeId);
	}
	
	@PUT
	@Path("/{noticeId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Notice updateNotice(@PathParam("noticeId") long noticeId, Notice notice) {
		final Notice fromDb = noticesService.getNotice(noticeId);
		fromDb.setTitle(notice.getTitle());
		fromDb.setDescription(notice.getDescription());
		return noticesService.updateNotice(fromDb);
	}
}
