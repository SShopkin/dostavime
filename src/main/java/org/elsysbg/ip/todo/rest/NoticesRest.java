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

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.elsysbg.ip.todo.entities.Notice;
import org.elsysbg.ip.todo.services.AuthenticationService;
import org.elsysbg.ip.todo.services.NoticesService;
import org.secnod.shiro.jaxrs.Auth;

@Path("/notices")
public class NoticesRest {
	private final NoticesService noticesService;
	private final AuthenticationService authenticationService;

	@Inject
	public NoticesRest(NoticesService noticesService,
			AuthenticationService authenticationService) {
		this.noticesService = noticesService;
		this.authenticationService = authenticationService;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Notice> getTasks() {
		return noticesService.getTasks();
	}
	
	@GET
	@Path("/{noticeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Notice getTask(@PathParam("noticeId") long noticeId) {
		return noticesService.getTask(noticeId);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequiresAuthentication
	public Notice createTask(@Auth Subject subject, Notice notice) {
		notice.setAuthor(authenticationService.getCurrentlyLoggedInMember(subject));
		return noticesService.createTask(notice);
	}
	
	@DELETE
	@Path("/{noticeId}")
	public void deleteTask(@PathParam("noticeId") long noticeId) {
		noticesService.deleteTask(noticeId);
	}
	
	@PUT
	@Path("/{noticeId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Notice updateTask(@PathParam("noticeId") long noticeId, Notice notice) {
		final Notice fromDb = noticesService.getTask(noticeId);
		fromDb.setTitle(notice.getTitle());
		fromDb.setDescription(notice.getDescription());
		return noticesService.updateTask(fromDb);
	}
}
