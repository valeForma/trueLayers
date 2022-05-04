package com.truelayers.pokemon.model;

import lombok.Data;

@Data
public class FlavorText {

	private String flavor_text;
	
	private NamedApiResource language;
	
	private NamedApiResource version;
	
}
