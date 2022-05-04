package com.truelayers.pokelink.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.truelayers.pokelink.model.DescriptionModel;
import com.truelayers.pokemon.model.FlavorText;

@Mapper(componentModel = "spring")
public interface FlavorTextMapper {

	@Mappings({
		@Mapping(source="language.name",target = "language"),
		@Mapping(source="version.name",target = "version")
	})
	DescriptionModel toDto(FlavorText flavorText);
	
	@InheritInverseConfiguration
	FlavorText fromDto(DescriptionModel description);
	
}
