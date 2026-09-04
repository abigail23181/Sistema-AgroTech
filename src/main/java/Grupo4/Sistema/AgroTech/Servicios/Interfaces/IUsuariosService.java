package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Usuarios;

import java.util.List;

public interface IUsuariosService {
    List<Usuarios> listarTodos();
    Usuarios guardarUsuario(IUsuariosService usuario);

    Usuarios guardarUsuario(Usuarios usuario);

    Usuarios obtenerPorId(Long id);
    void cambiarEstado(Long id, boolean activo);
    boolean existeUsername(String username);
    boolean existeCorreo(String correo);

    void guardarUsuarios(Usuarios usuario);
}