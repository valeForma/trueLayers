package com.truelayers.pokemon.model;

import java.util.List;

import lombok.Data;

@Data
public class Pagination {

	private Integer count;
	
	private String next;
	
	private String previous;
	
	private List<NamedApiResource> results;
}
