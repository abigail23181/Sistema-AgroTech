package Grupo4.Sistema.AgroTech.Repositorios;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoMantenimientoRepository extends JpaRepository<TipoMantenimiento, Long> {

    List<TipoMantenimiento> findByActivoTrue();

    boolean existsByNombreIgnoreCase(String nombre);
}