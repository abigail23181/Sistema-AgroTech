package Grupo4.Sistema.AgroTech.Repositorios;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    @Query("SELECT a FROM Alerta a WHERE " +
            "(:tipo IS NULL OR a.tipo LIKE %:tipo%) AND " +
            "(:ubicacion IS NULL OR a.ubicacion LIKE %:ubicacion%) AND " +
            "(:maquinariaId IS NULL OR a.maquinaria.idMaquinaria = :maquinariaId)")
    List<Alerta> buscarConFiltros(@Param("tipo") String tipo,
                                  @Param("ubicacion") String ubicacion,
                                  @Param("maquinariaId") Long maquinariaId);
}