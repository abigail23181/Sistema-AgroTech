package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUsuariosService {

    Optional<Usuarios> login(String correo, String clave);

    Page<Usuarios> obtenerTodosPaginados(Pageable pageable);

    List<Usuarios> obtenerTodos();

    List<Usuarios> listarTodos();

    Usuarios guardar(Usuarios usuario);

    Usuarios obtenerPorId(Long id);

    void cambiarEstado(Long id, boolean activo);

    Usuarios autenticar(String correo, String clave);

    boolean existeEmail(String correo);

    boolean existeUsername(String usuario);

    Usuarios crearOeditar(Usuarios usuario);

    void eliminarPorId(Long id);
}