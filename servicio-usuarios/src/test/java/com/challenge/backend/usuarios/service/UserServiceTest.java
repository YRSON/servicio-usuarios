package com.challenge.backend.usuarios.service;

import com.challenge.backend.usuarios.model.Product;
import com.challenge.backend.usuarios.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private UserService userService;

    @BeforeEach
    void setUp() {
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        String fakeUrl = "http://fake-url-para-test.com";
        userService = new UserService(webClientBuilder, fakeUrl);
    }

    @Test
    void findById_should_return_user_when_exists() {
        Mono<User> result = userService.findById(1L);
        StepVerifier.create(result)
                .expectNextMatches(user -> user.id().equals(1L) && user.nombre().equals("Yerson Sifuentes"))
                .verifyComplete();
    }

    @Test
    void getProductsFromOtherService() {
        Product mockProduct = new Product(100L, "Producto Falso", 99.9);
        Flux<Product> mockProductFlux = Flux.just(mockProduct);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/products")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(Product.class)).thenReturn(mockProductFlux);
        Flux<Product> resultFlux = userService.getProductsFromOtherService();
        StepVerifier.create(resultFlux)
                .expectNext(mockProduct)
                .verifyComplete();
    }

}
