package org.elsysbg.ip.todo.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.elsysbg.ip.todo.entities.NeedTransportUser;
import org.elsysbg.ip.todo.entities.Notice;
import org.elsysbg.ip.todo.services.NeedTransportUsersService;
import org.elsysbg.ip.todo.services.NoticesService;

@Path("/needTransportUsers")
public class NeedTransportUsersRest {
	private final NeedTransportUsersService needTransportUsersService;
	private final NoticesService noticesService;

	@Inject
	public NeedTransportUsersRest(NeedTransportUsersService needTransportUsersService,
		NoticesService noticesService) {
		this.needTransportUsersService = needTransportUsersService;
		this.noticesService = noticesService;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequiresGuest
	public NeedTransportUser createMember(NeedTransportUser needTransportUser) {
		return needTransportUsersService.createMember(needTransportUser);
	}
	
	@GET
	@Path("/{needTransportUserId}/notices")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Notice> getMemberTasks(
		@PathParam("needTransportUserId") long needTransportUserId) {
		final NeedTransportUser author = needTransportUsersService.getMember(needTransportUserId);
		return noticesService.getTasksByAuthor(author);
	}
}
