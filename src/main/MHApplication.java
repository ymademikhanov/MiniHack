package main;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/services")
public class MHApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();  
	private Set<Class<?>> empty = new HashSet<Class<?>>();

    private static final Logger logger = LogManager.getLogger();
	
	public MHApplication() {
		singletons.add(new MHFindPlaceService(new GoogleAutocomplete()));
		singletons.add(new MHGeocodeService());
		singletons.add(new MHSearchProperties(new DummySearcher()));

        logger.error("Loaded services, boy!");
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
