package org.elsysbg.ip.todo.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.subject.Subject;
import org.elsysbg.ip.todo.entities.NeedTransportUser;
import org.elsysbg.ip.todo.services.AuthenticationService;
import org.secnod.shiro.jaxrs.Auth;

@Path("/authentication")
public class AuthenticationRest {

	private final AuthenticationService authenticationService;

	@Inject
	public AuthenticationRest(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public NeedTransportUser login(@Auth Subject subject, NeedTransportUser needTransportUser) {
		authenticationService.login(subject,
				needTransportUser.getUsername(), needTransportUser.getPassword());
		return authenticationService.getCurrentlyLoggedInMember(subject);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public NeedTransportUser getCurrentlyLoggedInMember(@Auth Subject subject) {
		return authenticationService.getCurrentlyLoggedInMember(subject);
	}
	
	@DELETE
	public void logout(@Auth Subject subject) {
		authenticationService.logout(subject);
	}
}
