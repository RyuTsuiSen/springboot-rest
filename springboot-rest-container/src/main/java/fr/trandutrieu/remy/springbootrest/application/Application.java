package fr.trandutrieu.remy.springbootrest.application;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.SpringApplication;

import fr.trandutrieu.remy.socle.rest.springboot.ApplicationRestAbstract;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


public class Application extends ApplicationRestAbstract{
    
	public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean api() {
        ServletRegistrationBean publicJersey
                = new ServletRegistrationBean(new ServletContainer(new MyConfiguration()));
        publicJersey.addUrlMappings("/api/*");
        publicJersey.setName("api");
        publicJersey.setLoadOnStartup(0);
        return publicJersey;
    }
}
