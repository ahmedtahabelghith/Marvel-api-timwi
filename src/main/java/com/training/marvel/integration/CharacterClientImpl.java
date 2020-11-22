package com.training.marvel.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.training.marvel.model.CharacterData;
import com.training.marvel.util.CharacterUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CharacterClientImpl implements CharacterClient {
    
	private WebClient webClient;
	
	@Value("${marvelApiPublicKey}")
	public String publicKey;
	@Value("${marvelApiPrivateKey}")
	public String privateKey;
    
	public CharacterClientImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://gateway.marvel.com:443/v1/public/").build();
	}
    
	@Cacheable(value = "characters")
	@Override
	public Flux<CharacterData> getCharacters() {
	
		return this.webClient.get()
               .uri(uriBuilder -> uriBuilder.path("/characters")
	                .queryParam("ts",  CharacterUtil.timeStamp)
	                .queryParam("apikey",publicKey)
	                .queryParam("hash", CharacterUtil.MD5hash(publicKey, privateKey,CharacterUtil.timeStamp))
	                .build())
	                .retrieve()
	                .bodyToFlux(CharacterData.class).cache().retry();
	}
    
	@Override
	public Mono<CharacterData> getCharactersById(int id) {		
		return this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/characters/" + id)
	                .queryParam("ts",  CharacterUtil.timeStamp)
	                .queryParam("apikey",publicKey)
	                .queryParam("hash", CharacterUtil.MD5hash(publicKey, privateKey,CharacterUtil.timeStamp))
	                .build())
					.retrieve()
					.bodyToMono(CharacterData.class).retry();
	}

	
}
