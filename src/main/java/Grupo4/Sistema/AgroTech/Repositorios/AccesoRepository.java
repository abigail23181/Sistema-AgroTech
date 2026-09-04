package Grupo4.Sistema.AgroTech.Repositorios;

import Grupo4.Sistema.AgroTech.Model.Acceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, Long> { // <-- Debe ser Acceso, NO AccesoRepository

    // Método personalizado para buscar por email
    Acceso findByEmail(String email);
}