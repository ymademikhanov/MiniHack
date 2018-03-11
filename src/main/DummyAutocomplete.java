package main;

import java.util.ArrayList;
import java.util.List;

public class DummyAutocomplete implements Autocomplete {
	private List<Suggestion> suggestions = new ArrayList<Suggestion>();
	
	public DummyAutocomplete() {
		String[] places = {"Astana", "Amsterdam", "Alabama", "Austria", "Australia", "Almaty", "Aktau", "Aktobe", "Aral", "Atyrau", "Astrakhan", "Akkuduk"};
		for (String place : places) {
			suggestions.add(new Suggestion(place, "ChIJN1t_tDeuEmsRUsoyG83frY4"));
		}
	}

	@Override
	public List<Suggestion> autocomplete(String input) {
		return suggestions;
	}
}
