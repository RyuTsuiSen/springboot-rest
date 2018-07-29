package fr.trandutrieu.remy.springbootrest.application.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import fr.trandutrieu.remy.socle.exceptions.BusinessException;
import fr.trandutrieu.remy.socle.exceptions.BusinessException.BusinessExceptionBuilder;
import fr.trandutrieu.remy.socle.externalcall.AdapterCall.TYPE_APPEL;
import fr.trandutrieu.remy.socle.webservices.inout.BusinessResponse;
import fr.trandutrieu.remy.socle.webservices.rest.Resource;

@Component
@Path("hello")
public class Hello implements Resource{

	
	@GET
	@Produces("application/json")
	public BusinessResponse getHello() {
		BusinessResponse response = new BusinessResponse();
		
		response.setReponse("Hello You");
		return response;
	}
	
	@GET
	@Path("runtime")
	@Produces("application/json")
    public BusinessResponse sayRuntimeException() {
    	throw new RuntimeException("une erreur grave est survenue");
    }

	@GET
	@Path("exception")
	@Produces("application/json")
	public BusinessResponse sayBusinessException() throws BusinessException {
		throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).build();
	}
	
	@GET
	@Path("null")
	@Produces("application/json")
	public BusinessResponse sayHelloButNullPointer(){
    	BusinessResponse reponse = null;
    	reponse.setReponse("Hello, Welcome to CXF Spring boot " + TYPE_APPEL.ERREUR_DEV + "!!!");
		return reponse;
	}
}
