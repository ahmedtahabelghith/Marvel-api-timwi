package com.training.marvel.model;

import lombok.Data;

@Data
public class Character {
	
	private int id;
	private String name;
	private String description;
	private Thumbnail thumbnail;
}