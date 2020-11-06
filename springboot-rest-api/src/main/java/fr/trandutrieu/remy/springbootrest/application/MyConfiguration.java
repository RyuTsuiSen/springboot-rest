package fr.trandutrieu.remy.springbootrest.application;

import fr.trandutrieu.remy.socle.rest.JerseyConfiguration;
import fr.trandutrieu.remy.springbootrest.application.resource.Hello;

public class MyConfiguration extends JerseyConfiguration {
	public void init() {
		register(Hello.class);
	}
}