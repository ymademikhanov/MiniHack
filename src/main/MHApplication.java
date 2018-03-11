package main;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/services")
public class MHApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();  
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	public MHApplication() {
		singletons.add(new MHFindPlaceService(new GoogleAutocomplete()));
		singletons.add(new MHGeocodeService());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
