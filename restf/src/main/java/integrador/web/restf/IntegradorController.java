package integrador.web.restf;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/integrador")
public class IntegradorController {

    @Autowired
    private FacturaClient facturaClient;

    @Autowired
    private ProductoClient productoClient;

    @GetMapping("/facturas-con-productos")
    public Mono<List<FacturaConProductos>> getFacturasConProductos() {
        // Obtén las facturas y los productos desde los clientes
        Mono<List<Factura>> facturasMono = facturaClient.obtenerFacturas();
        Mono<List<Producto>> productosMono = productoClient.obtenerProductos();

        // Combina las respuestas
        return Mono.zip(facturasMono, productosMono, (facturas, productos) -> {
            // Aquí, combinamos las facturas y productos
            return facturas.stream().map(factura -> {
                FacturaConProductos facturaConProductos = new FacturaConProductos();
                facturaConProductos.setFactura(factura);
                facturaConProductos.setProductos(productos); // Asignar productos de ejemplo
                return facturaConProductos;
            }).toList();
        });
    }
}
