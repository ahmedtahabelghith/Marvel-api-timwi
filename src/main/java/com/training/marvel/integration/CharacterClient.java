package com.training.marvel.integration;

import com.training.marvel.model.CharactersData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CharacterClient {
	
	public Flux<CharactersData> getCharacters();
	public Mono<CharactersData> getCharactersById(int id);
	public Mono<CharactersData> getCharactersPowers(int id);
}
