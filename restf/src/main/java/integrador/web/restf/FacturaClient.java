package integrador.web.restf;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Component
public class FacturaClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String FACTURA_SERVICE_URL = "http://localhost:8080/api/facturas"; // Ajusta la URL según sea necesario

    public Mono<List<Factura>> obtenerFacturas() {
        return webClientBuilder.baseUrl(FACTURA_SERVICE_URL)
                .build()
                .get()
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Factura>>(){}); // Usamos ParameterizedTypeReference
    }
}
