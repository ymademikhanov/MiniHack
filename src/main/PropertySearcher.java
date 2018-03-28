package main;

import java.util.List;

public interface PropertySearcher {
	public List<Property> getSearchResults(String location, int minprice, int maxprice);
}
