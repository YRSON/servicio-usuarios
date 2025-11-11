package com.challenge.backend.usuarios.service;

import com.challenge.backend.usuarios.model.Product;
import com.challenge.backend.usuarios.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> userDataBase = new ArrayList<>(List.of(
            new User(1L, "Yerson Sifuentes", "yerson@gmail.com"),
            new User(2L, "Joselyn Ledesma", "joselyn@gmail.com"),
            new User(3L, "Aldair Sifuentes", "aldair@gmail.com")
    ));

    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public Flux<User> findAll(){
        return Flux.fromIterable(userDataBase);
    }

    public Mono<User> findById(Long id){
        return Flux.fromIterable(userDataBase)
                .filter(u -> u.id().equals(id))
                .next();
    }

    public Flux<Product> getProductsFromOtherService() {
        return this.webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }

}
