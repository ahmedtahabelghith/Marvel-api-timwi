package com.training.marvel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.training.marvel.integration.CharacterClient;
import com.training.marvel.model.Character;
import com.training.marvel.model.CharactersData;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CharacterController {
	@Autowired
	private CharacterClient characterService;
	
	@Autowired
	public CharacterController(CharacterClient characterService) {
		this.characterService = characterService;
	}

	@ApiOperation(value = "Get  characters", notes = "Provides characters ids")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of characters", response = Character.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/characters")
	public Flux<CharactersData> getCaracters() {
		return characterService.getCharacters();
	}

	@ApiOperation(value = "Get characters by ID", notes = "Provides characters by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of character", response = Character.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("/characters/{characterId}")
	public Mono<CharactersData> getCharactersById(@PathVariable("characterId") int id) {
		return characterService.getCharactersById(id);
	}

}
