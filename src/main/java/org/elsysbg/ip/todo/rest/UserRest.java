package org.elsysbg.ip.todo.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.todo.entities.User;
import org.elsysbg.ip.todo.services.UsersService;
import org.elsysbg.ip.todo.services.NoticesService;

@Path("/users")
public class UserRest {
	private final UsersService usersService;
	@Inject
	public UserRest(UsersService usersService,
		NoticesService noticesService) {
		this.usersService = usersService;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User createUser(User user) {
		return usersService.createUser(user);
	}
	
}
