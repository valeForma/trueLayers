package com.truelayers.pokelink.model;

import java.util.List;

import com.truelayers.pokemon.model.FlavorText;

import lombok.Data;

@Data
public class PokemonModel {

	private Integer id;
	
	private String name;
	
	private List<DescriptionModel> description;
	
	private String shakespeareDescription;
}
