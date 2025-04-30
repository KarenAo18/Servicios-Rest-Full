package integrador.web.restf;

public class Factura {

    private Long id;
    private Double total;

    // Constructor
    public Factura(Long id, Double total) {
        this.id = id;
        this.total = total;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
