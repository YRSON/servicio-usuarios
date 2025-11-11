package com.challenge.backend.usuarios.controller;

import com.challenge.backend.usuarios.model.Product;
import com.challenge.backend.usuarios.model.User;
import com.challenge.backend.usuarios.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/productos-de-otro-microservicio")
    public Flux<Product> getProductsFromProductsService(){
        return userService.getProductsFromOtherService();
    }
}
