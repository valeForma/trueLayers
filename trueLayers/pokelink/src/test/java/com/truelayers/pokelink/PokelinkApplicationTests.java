package com.truelayers.pokelink;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Collectors;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.truelayers.pokelink.model.PokemonModel;
import com.truelayers.pokelink.service.PokemonService;

@SpringBootTest
class PokelinkApplicationTests {

	@Autowired
	PokemonService pokemonService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Test
	void contextLoads() {
	}
	
	private MockMvc mockMvc;
	@BeforeEach
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	void testPokemon() throws Exception{
		
		this.mockMvc.perform(get("/v1/pokemon/search/bulbasaur"))
			      .andDo(print()).andExpect(status().isOk())
			      .andExpect(jsonPath("$.name").value("bulbasaur"))
			      .andReturn();
			    
	  
	}
	
	@Test
	void testPokemonNotFound() throws Exception{
		
		 this.mockMvc.perform(get("/v1/pokemon/search/pinuccio"))
			      .andDo(print()).andExpect(status().is4xxClientError())
			      .andReturn();
			    
	  
	}
	
	@Test
	void testPokemonAvailable() throws Exception{
		
	this.mockMvc.perform(get("/v1/pokemon/list")
				.param("offset", "0")
				.param("limit", "10"))
			      .andDo(print()).andExpect(status().isOk())
			      .andExpect(jsonPath("$.results", hasSize(10)));
			      
			    
	  
	}
	
	@Test
	void testPokemonAvailablNotFound() throws Exception{
		
		 this.mockMvc.perform(get("/v1/pokemon/list")
					.param("offset", "2000")
					.param("limit", "10"))
		 				.andDo(print()).andExpect(status().isOk())
		 				.andExpect(jsonPath("$.results", hasSize(0)));
	  
	}

	@Test
	void testShakespeareTranslate() throws Exception{
		
	this.mockMvc.perform(get("/v1/pokemon/translate")
				.param("content", "this is a Test"))
			      .andDo(print()).andExpect(status().isOk())
			      .andExpect(jsonPath("$.contents.translated").value("This is a test"));
			      
			    
	  
	}
}
