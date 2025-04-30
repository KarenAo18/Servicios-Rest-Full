package integrador.web.restf;

import java.util.List;

public class FacturaConProductos {

    private Factura factura;
    private List<Producto> productos;

    // Getter y setter para Factura
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    // Getter y setter para Productos
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
