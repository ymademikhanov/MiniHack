package main;

import java.util.List;

public interface Autocomplete {
	public void autocomplete(String input);
	public List<Suggestion> suggestions();
}
