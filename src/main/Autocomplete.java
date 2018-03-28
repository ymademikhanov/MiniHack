package main;

import java.util.List;

public interface Autocomplete {
	public List<Suggestion> autocomplete(String input);
}
