package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Usuarios;
import java.util.List;

public interface IUsuariosService {
    List<Usuarios> listarTodos();
    Usuarios guardar(Usuarios usuario);
    Usuarios obtenerPorId(Long id);
    void cambiarEstado(Long id, boolean activo);
    Usuarios autenticar(String email, String password);
    boolean existeEmail(String email);
    boolean existeUsername(String username);
}