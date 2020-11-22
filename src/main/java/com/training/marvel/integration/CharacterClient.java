package com.training.marvel.integration;

import com.training.marvel.model.CharacterData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CharacterClient {
	
	public Flux<CharacterData> getCharacters();
	public Mono<CharacterData> getCharactersById(int id);

}
