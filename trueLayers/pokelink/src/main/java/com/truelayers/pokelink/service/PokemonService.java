package com.truelayers.pokelink.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.truelayers.pokelink.exception.CustomInternalException;
import com.truelayers.pokelink.feign.service.PokemonClientService;
import com.truelayers.pokelink.feign.service.ShakespeareTranslateClientService;
import com.truelayers.pokelink.mapper.PaginationMapper;
import com.truelayers.pokelink.mapper.PokemonMapper;
import com.truelayers.pokelink.model.PaginationModel;
import com.truelayers.pokelink.model.PokemonModel;
import com.truelayers.pokemon.model.Pokemon;
import com.truelayers.shakespeare.model.ShakespeareTranslation;

@Service
public class PokemonService {

	@Autowired
	PokemonMapper pokemonMapper;
	
	@Autowired
	PaginationMapper paginationMapper;
	
	@Autowired
	PokemonClientService pokemonClientService;
	
	
	
	public PokemonModel retrievePokemonById(String name) throws Exception{
		return Optional.ofNullable(pokemonClientService.getPokemonByname(name)).map(x->pokemonMapper.toDto(x))
				.orElseThrow(()->new CustomInternalException(String.format("cannot found pokemon named %s" ,name),HttpStatus.NOT_FOUND));
	
	}
	
	public PaginationModel listAvailablePokemon(Integer offset,Integer limit)  throws Exception{
		return Optional.ofNullable(pokemonClientService.getAvailablePokemon(offset,limit)).map(x->paginationMapper.toDto(x))
				.orElseThrow(()->new CustomInternalException(String.format("cannot found any valid  pokemon for offset %d,limit to %d",offset,limit),HttpStatus.NOT_FOUND));
	}
}
