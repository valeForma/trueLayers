package com.truelayers.pokelink.model;

import java.util.List;

import lombok.Data;

@Data
public class PaginationModel {

	private Integer count;
	
	private String next;
	
	private String previous;
	
	private List<String> results;
}
