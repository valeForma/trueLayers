package com.truelayers.pokemon.model;

import java.util.List;

import lombok.Data;

@Data
public class Pokemon {

	
	private Integer id;
	
	private String name;
	
	private Integer order;	
	
	private Integer gender_rate;
	
	private Integer capture_rate;
	
	private Integer base_happiness;
	
	private Boolean is_baby	;
	
	private Boolean is_legendary;	

	private Boolean is_mythical	;
	
	private Integer hatch_counter;	
	
	private Boolean has_gender_differences;
	
	private Boolean forms_switchable;	
	
	private NamedApiResource  growth_rate;
	
	private List<NamedApiResource> pokedex_numbers;
	
	private List<NamedApiResource> egg_groups;	
	
	private NamedApiResource color;	
	
	private NamedApiResource shape;	
	
	private NamedApiResource evolves_from_species;	
	
	private ApiResource evolution_chain	;
	
	private NamedApiResource habitat;	
	
	private NamedApiResource generation;
	
	private List<Name> names;	
	
	private List<PalParkEncounterArea>  pal_park_encounters;	
	
	private List<FlavorText>  flavor_text_entries;	
	
	private List<Description>  form_descriptions;
	
	private List<Genus> genera;	
	
	private List<PokemonSpeciesVariety>  varieties;	
		
}
