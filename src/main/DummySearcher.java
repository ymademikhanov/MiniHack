package main;

import java.util.ArrayList;
import java.util.List;

public class DummySearcher implements PropertySearcher {
	private List<Property> properties;
	public DummySearcher() {
		properties = new ArrayList<Property>();
		properties.add(new Property("Shadwell London, UK", 51.511480, -0.055245, 600));
		properties.add(new Property("Old Ford Road London, UK", 51.533246, -0.041347, 500));
		properties.add(new Property("Camden London, UK", 51.551706, -0.158826, 900));
		properties.add(new Property("Farringdon London, UK", 51.511706, -0.158926, 1200));
	}
	
	public List<Property> getSearchResults(String location, int minprice, int maxprice) {
		List<Property> result = new ArrayList<Property>();
		for (Property p : properties) {
			if (p.doesBelong(location) && p.price >= minprice && p.price <= maxprice)
				result.add(p);
		}
		return result;
	}
}
