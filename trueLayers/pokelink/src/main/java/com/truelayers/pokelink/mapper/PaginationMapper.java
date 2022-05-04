package com.truelayers.pokelink.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.truelayers.pokelink.model.PaginationModel;
import com.truelayers.pokemon.model.NamedApiResource;
import com.truelayers.pokemon.model.Pagination;

@Mapper(componentModel = "spring",uses = FlavorTextMapper.class)
public interface PaginationMapper {

	@Mappings({
		@Mapping(source = "results",target = "results" ,qualifiedByName = "toListOfString"),
	})
	PaginationModel toDto(Pagination list);
	
	@InheritInverseConfiguration
	@Mappings({
		@Mapping(source = "results",target = "results" ,qualifiedByName = "toListOfNamedApiRes"),
	})
	Pagination  fromDto(PaginationModel dto);
	
    List<PaginationModel> toDto(List<Pagination> list);
	
	
    List<Pagination>  fromDto(List<PaginationModel> dto);
	
	@Named("toListOfString")
	default List<String> toListOfString(List<NamedApiResource> results){
		if(results==null|| results.isEmpty()) {
			return new ArrayList<String>();
		}
		return results.stream().map(x->x.getName()).collect(Collectors.toList());
	}
	
	@Named("toListOfNamedApiRes")
	default List<NamedApiResource> toListOfNamedApiRes(List<String> results){
		if(results==null|| results.isEmpty()) {
			return new ArrayList<NamedApiResource>();
		}
		return results.stream().map(x-> new NamedApiResource(x)).collect(Collectors.toList());
	}
	
}
