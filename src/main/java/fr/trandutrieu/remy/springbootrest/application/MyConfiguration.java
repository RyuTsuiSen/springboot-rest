package fr.trandutrieu.remy.springbootrest.application;

import org.springframework.stereotype.Component;

import fr.trandutrieu.remy.socle.rest.JerseyConfiguration;
import fr.trandutrieu.remy.springbootrest.application.resource.Hello;

@Component
public class MyConfiguration extends JerseyConfiguration {
	public void init() {
		register(Hello.class);
	}
}