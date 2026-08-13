package Grupo4.Sistema.AgroTech.Repositorios;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByCorreo(String correo);

    Optional<Empresa> findByRuc(String ruc);

    boolean existsByCorreo(String correo);

    boolean existsByRuc(String ruc);

    boolean existsByCorreoAndIdNot(String correo, Long id);

    boolean existsByRucAndIdNot(String ruc, Long id);
}