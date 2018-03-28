package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/searchProperties")
public class MHSearchProperties {
private PropertySearcher searcher;
	
	public MHSearchProperties(PropertySearcher searcher) {
		this.searcher = searcher;
	}
	
	@GET
	public Response getList(@QueryParam("location") String location, @QueryParam("minprice") int minprice, @QueryParam("maxprice") int maxprice) {
		String result = new Gson().toJson(this.searcher.getSearchResults(location, minprice, maxprice));
//		System.out.println(result);
		return Response.ok(result).build();
	}
}
