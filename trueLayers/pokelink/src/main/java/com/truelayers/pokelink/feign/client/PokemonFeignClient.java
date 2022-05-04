package com.truelayers.pokelink.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.truelayers.pokemon.model.Pagination;
import com.truelayers.pokemon.model.Pokemon;

import feign.Headers;

@FeignClient(name = "pokemonFeignClient",url= "${feign.pokemon.url}")
public interface PokemonFeignClient {

	@Headers({"Host: pokeBackhost","Accept: */*"})
	@RequestMapping(path = "/pokemon-species" ,method = RequestMethod.GET,produces = "application/json")
	ResponseEntity<Pagination> getAvailablePokemon(@RequestParam("offset")Integer offset,
			@RequestParam("limit")Integer limit) throws Exception;
	
	@Headers({"Host: pokeBackhost","Accept: */*"})
	@RequestMapping(path = "/pokemon-species/{name}/" ,method = RequestMethod.GET,produces = "application/json")
	ResponseEntity<Pokemon> getPokemonByname(@PathVariable("name")String name) throws Exception;
}
