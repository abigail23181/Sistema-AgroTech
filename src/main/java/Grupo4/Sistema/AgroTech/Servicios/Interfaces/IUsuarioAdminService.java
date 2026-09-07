package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.UsuarioAdmin;

import java.util.List;

public interface IUsuarioAdminService {

    List<UsuarioAdmin> listarTodos();

    UsuarioAdmin buscarPorId(Long id);

    UsuarioAdmin buscarPorUsuario(String usuario);

    void guardar(UsuarioAdmin usuario);

    void eliminar(Long id);

    void cambiarEstado(Long id);

    boolean usuarioExiste(String usuario);

    boolean emailExiste(String email);

    boolean usuarioExisteEditando(String usuario, Long id);

    boolean emailExisteEditando(String email, Long id);
}