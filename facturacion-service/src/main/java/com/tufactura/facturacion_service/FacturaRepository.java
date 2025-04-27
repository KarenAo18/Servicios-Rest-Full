package com.tufactura.facturacion_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    // Consulta derivada automática
    List<Factura> findByClienteContainingIgnoreCase(String cliente);

    // Consulta personalizada con JPQL
    @Query("SELECT f FROM Factura f WHERE f.fecha BETWEEN :inicio AND :fin ORDER BY f.fecha DESC")
    List<Factura> buscarPorRangoFechas(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

    // Consulta nativa para estadísticas
    @Query(value = """
            SELECT EXTRACT(MONTH FROM f.fecha) as mes, 
                   COUNT(*) as total, 
                   SUM(f.total) as ventas 
            FROM facturas f 
            WHERE EXTRACT(YEAR FROM f.fecha) = :anio 
            GROUP BY mes 
            ORDER BY mes""", 
            nativeQuery = true)
    List<Object[]> obtenerEstadisticasMensuales(@Param("anio") int anio);

    // Consulta con proyección
    @Query("SELECT f.cliente, f.total FROM Factura f WHERE f.total > :montoMinimo")
    List<Object[]> findClientesConFacturasMayoresA(@Param("montoMinimo") Double montoMinimo);
}