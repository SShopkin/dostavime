package org.elsysbg.ip.todo;

import org.elsysbg.ip.todo.services.AuthenticationService;
import org.elsysbg.ip.todo.services.EntityManagerService;
import org.elsysbg.ip.todo.services.UsersService;
import org.elsysbg.ip.todo.services.NoticesService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class DostaviMeServletContextListener extends GuiceServletContextListener {

	public static Injector injector;

	@Override
	protected Injector getInjector() {
		if (injector == null) {
			injector = Guice.createInjector(new ServletModule() {
				@Override
				protected void configureServlets() {
					bind(NoticesService.class);
					bind(EntityManagerService.class);
					bind(UsersService.class);
					bind(AuthenticationService.class);
				}
			});
		}

		return injector;
	}
}
