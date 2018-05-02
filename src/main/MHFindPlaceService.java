package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/findPlace")
public class MHFindPlaceService {
	private Autocomplete autocompleter;
	
	public MHFindPlaceService(Autocomplete autocompleter) {
		this.autocompleter = autocompleter;
	}
	
	@GET
	public Response getList(@QueryParam("input") String input) {
		System.out.println("got query");
		String result = new Gson().toJson(this.autocompleter.autocomplete(input));
		
		
		return Response.ok(result).build();
	}
}