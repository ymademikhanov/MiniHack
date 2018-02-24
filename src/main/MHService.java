package main;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;


@Path("/todo")
public class MHService {

	public MHService() {
	}
	
	@GET
	public Response getList(@QueryParam("id") String id) {
		String result;
		
		return null;
	}
	
	@POST
	@Consumes("application/json")
	public Response postInstance(String data) {
		ResponseBuilder builder;
		return null;
	}
	
	@DELETE
	public Response deleteInstance(@QueryParam("id") String id) {
		ResponseBuilder builder;
		return null;
	}
	
}