package fr.trandutrieu.remy.springbootrest.application.resource;

import fr.trandutrieu.remy.socle.audit.Audit;
import fr.trandutrieu.remy.socle.audit.Audit.Level;
import fr.trandutrieu.remy.socle.exceptions.BusinessException;
import fr.trandutrieu.remy.socle.exceptions.BusinessException.BusinessExceptionBuilder;
import fr.trandutrieu.remy.socle.externalcall.AdapterCall.TYPE_APPEL;
import fr.trandutrieu.remy.socle.inout.BusinessResponse;
import fr.trandutrieu.remy.socle.rest.Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.TimeUnit;

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
	
	@GET
	@Path("asyncFaux")
    public void getAsyncFaux(@Suspended final AsyncResponse asyncResponse) throws InterruptedException {
		Audit.trace(Level.INFO, "ASYNC", "MAIN - Debut getAsyncFaux");
		TimeUnit.SECONDS.sleep(10);
		Audit.trace(Level.INFO, "ASYNC", "MAIN - Fin getAsyncFaux");
		asyncResponse.resume("Fin en asynchrone faux");
    }
	
	@GET
	@Path("asyncReel")
    public void getAsyncReel(@Suspended final AsyncResponse asyncResponse) {
		Audit.trace(Level.INFO, "ASYNC", "MAIN - Debut getAsyncReel");
		new Thread(new Runnable() {
            @Override
            public void run() {
        		Audit.trace(Level.INFO, "ASYNC", "THREAD - Debut en asynchrone vrai");
        		try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		Audit.trace(Level.INFO, "ASYNC", "THREAD - Fin en asynchrone vrai");
        		asyncResponse.resume("Fin en asynchrone vrai");
            }
        }).start();
		Audit.trace(Level.INFO, "ASYNC", "MAIN - Fin getAsyncReel");
    }
}
