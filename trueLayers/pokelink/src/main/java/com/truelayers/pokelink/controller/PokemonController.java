package com.truelayers.pokelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truelayers.pokelink.model.PokemonModel;
import com.truelayers.pokelink.service.PokemonService;
import com.truelayers.pokelink.service.ShakespeareTranslationService;
import com.truelayers.shakespeare.model.ShakespeareTranslation;
@RestController
@RequestMapping("/v1/pokemon")
public class PokemonController {

	@Autowired
	PokemonService pokemonService;
	
	@Autowired
	ShakespeareTranslationService shakespeareTranslationService;
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(path = "/search/{name}",method = RequestMethod.GET,produces = "application/json" )
	public ResponseEntity<PokemonModel> getAndTranslate(@PathVariable("name")String name) throws Exception {
		return new ResponseEntity(pokemonService.retrievePokemonById(name),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(path = "/list",method = RequestMethod.GET,produces = "application/json" )
	public ResponseEntity<PokemonModel> listAvailable(@RequestParam("offset")Integer offset,
			@RequestParam("limit")Integer limit) throws Exception {
		return new ResponseEntity(pokemonService.listAvailablePokemon(offset,limit),HttpStatus.OK);
	}
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(path = "/translate",method = RequestMethod.GET,produces = "application/json" )
	public ResponseEntity<ShakespeareTranslation> traslateToShakespeare(@RequestParam("content")String content) throws Exception {
		return new ResponseEntity(shakespeareTranslationService.translateContent(content),HttpStatus.OK);
	}
}
