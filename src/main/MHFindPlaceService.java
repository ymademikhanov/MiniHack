package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/findPlace")
public class MHFindPlaceService {
	private Autocomplete autocompleter;

	private static final Logger logger = LogManager.getLogger();

	public MHFindPlaceService(Autocomplete autocompleter) {
		this.autocompleter = autocompleter;
		logger.error("Autocomplete is loaded.");
        logger.info("Autocomplete is loaded by INFO.");
	}
	
	@GET
	public Response getList(@QueryParam("input") String input) {
		String result = new Gson().toJson(this.autocompleter.autocomplete(input));
		return Response.ok(result).build();
	}
}