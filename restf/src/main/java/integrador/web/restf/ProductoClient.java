package integrador.web.restf;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Component
public class ProductoClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String PRODUCTO_SERVICE_URL = "http://localhost:8081/api/productos"; // Ajusta la URL según sea necesario

    public Mono<List<Producto>> obtenerProductos() {
        return webClientBuilder.baseUrl(PRODUCTO_SERVICE_URL)
                .build()
                .get()
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Producto>>(){}); // Usamos ParameterizedTypeReference
    }
}
