package org.elsysbg.ip.todo.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.elsysbg.ip.todo.entities.TransportUser;
import org.elsysbg.ip.todo.entities.User;

@Singleton
public class AuthenticationService {
	private final UsersService usersService;
	private final TransportUsersService transportUsersService;

	@Inject
	public AuthenticationService(UsersService usersService,TransportUsersService transportUsersService) {
		this.usersService = usersService;
		this.transportUsersService = transportUsersService;
	}

	public User getCurrentlyLoggedInUser() {
		// TODO get currently logged user from security framework
		final List<User> users = usersService.getUsers();
		return users.iterator().next();
		// or return users.get(0);
	}
	
	public TransportUser getCurrentlyLoggedInTransportUser() {
		// TODO get currently logged user from security framework
		final List<TransportUser> transportUsers = transportUsersService.getTransportUsers();
		return transportUsers.iterator().next();
		// or return users.get(0);
	}

}
