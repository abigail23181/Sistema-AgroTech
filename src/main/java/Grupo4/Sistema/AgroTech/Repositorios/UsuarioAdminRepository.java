package Grupo4.Sistema.AgroTech.Repositorios;

import Grupo4.Sistema.AgroTech.Model.UsuarioAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin, Long> {

    Optional<UsuarioAdmin> findByUsuario(String usuario);

    boolean existsByUsuario(String usuario);

    boolean existsByEmail(String email);

    boolean existsByUsuarioAndIdNot(String usuario, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}