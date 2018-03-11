package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/findPlace")
public class MHService {
	private Autocomplete autocompleter;
	
	public MHService() {
		this.autocompleter = new DummyAutocomplete();
	}
	
	@GET
	public Response getList(@QueryParam("input") String input) {
		this.autocompleter.autocomplete(input);
		String result = new Gson().toJson(this.autocompleter.suggestions());
		
		System.out.println(result);
		
		return Response.ok(result).build();
	}
}