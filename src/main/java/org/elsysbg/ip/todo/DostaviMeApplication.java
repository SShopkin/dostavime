package org.elsysbg.ip.todo;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

public class DostaviMeApplication extends ResourceConfig {

	public DostaviMeApplication() {
	}

	@Inject
	public DostaviMeApplication(ServiceLocator serviceLocator) {
		this();

		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
		final GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(DostaviMeServletContextListener.injector);
	}
}
