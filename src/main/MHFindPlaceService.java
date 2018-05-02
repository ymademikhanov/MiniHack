package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import com.google.gson.Gson;

@Path("/findPlace")
public class MHFindPlaceService {
	private Autocomplete autocompleter;
	static Logger log = Logger.getLogger(MHFindPlaceService.class.getName());
	
	public MHFindPlaceService(Autocomplete autocompleter) {
		log.info("initialised.");
		this.autocompleter = autocompleter;
	}
	
	@GET
	public Response getList(@QueryParam("input") String input) {
		
		log.info("got query.");
		String result = new Gson().toJson(this.autocompleter.autocomplete(input));
		
		
		return Response.ok(result).build();
	}
}