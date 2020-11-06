package fr.trandutrieu.remy.springbootrest.application; /**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import fr.trandutrieu.remy.socle.rest.springboot.ApplicationWebRestAbstract;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


public class ApplicationWeb extends ApplicationWebRestAbstract{
   
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationWeb.class);
    }
    
	public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationWeb.class, args);
    }

    @Bean
    public ServletRegistrationBean api() {
        ServletRegistrationBean publicJersey
                = new ServletRegistrationBean(new ServletContainer(new MyConfiguration()));
        publicJersey.addUrlMappings("/api/*");
        publicJersey.setName("api");
        publicJersey.setLoadOnStartup(1);
        return publicJersey;
    }
}
