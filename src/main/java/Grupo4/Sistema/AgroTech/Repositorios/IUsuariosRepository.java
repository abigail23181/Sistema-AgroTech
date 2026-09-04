package Grupo4.Sistema.AgroTech.Repositorios;

import Grupo4.Sistema.AgroTech.Model.Usuarios; // <-- Debe ser la Entidad @Entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Long> { // <-- Usuarios (Entidad), Long (ID)
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
    Optional<Usuarios> findByUsername(String username);
}