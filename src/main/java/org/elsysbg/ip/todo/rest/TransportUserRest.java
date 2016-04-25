package org.elsysbg.ip.todo.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.todo.entities.TransportUser;
import org.elsysbg.ip.todo.services.TransportUsersService;
import org.elsysbg.ip.todo.services.NoticesService;

@Path("/transportUsers")
public class TransportUserRest {
	private final TransportUsersService transportUsersService;
	@Inject
	public TransportUserRest(TransportUsersService transportUsersService,
		NoticesService noticesService) {
		this.transportUsersService = transportUsersService;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public TransportUser createTransportUser(TransportUser transportUser) {
		return transportUsersService.createTransportUser(transportUser);
	}
	
}
