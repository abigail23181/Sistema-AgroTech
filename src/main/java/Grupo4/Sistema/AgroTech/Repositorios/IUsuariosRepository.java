package Grupo4.Sistema.AgroTech.Repositorios;

import Grupo4.Sistema.AgroTech.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByEmail(String email);
    Usuarios findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}