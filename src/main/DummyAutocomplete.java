package main;

import java.util.ArrayList;
import java.util.List;

public class DummyAutocomplete implements Autocomplete {
	private List<Suggestion> suggestions = new ArrayList<Suggestion>();
	
	public DummyAutocomplete() {
		String[] places = {"Astana", "Amsterdam", "Alabama", "Austria", "Australia", "Almaty", "Aktau", "Aktobe", "Aral", "Atyrau", "Astrakhan", "Akkuduk"};
//		int counter = 0;
		for (String place : places) {
			suggestions.add(new Suggestion(place, "ChIJN1t_tDeuEmsRUsoyG83frY4"));
		}
	}

	@Override
	public void autocomplete(String input) {
	}

	@Override
	public List<Suggestion> suggestions() {
		return suggestions;
	}
}
