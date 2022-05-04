package com.truelayers.pokelink.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.truelayers.pokelink.model.PokemonModel;
import com.truelayers.pokemon.model.Pokemon;

@Mapper(componentModel = "spring",uses = FlavorTextMapper.class)
public interface PokemonMapper {

	@Mappings({
		@Mapping(source = "flavor_text_entries",target = "description"),
	})
	PokemonModel toDto(Pokemon model);
	
	@InheritInverseConfiguration
	Pokemon  fromDto(PokemonModel pokemonWrapper);
	
	
}
