package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Usuarios;
import Grupo4.Sistema.AgroTech.Repositorios.UsuarioRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IUsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    private UsuarioRepository usuariosRepository;


    // ==========================
    // LOGIN
    // ==========================

    @Override
    public Optional<Usuarios> login(String correo, String clave) {

        Usuarios usuario = usuariosRepository
                .findByCorreo(correo)
                .orElse(null);

        if (usuario != null
                && usuario.getClave().equals(clave)
                && usuario.isActivo()) {

            return Optional.of(usuario);
        }

        return Optional.empty();
    }


    // ==========================
    // LISTAR PAGINADO
    // ==========================

    @Override
    public Page<Usuarios> obtenerTodosPaginados(Pageable pageable) {
        return usuariosRepository.findAll(pageable);
    }


    // ==========================
    // OBTENER TODOS
    // ==========================

    @Override
    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }


    // ==========================
    // LISTAR TODOS
    // ==========================

    @Override
    public List<Usuarios> listarTodos() {
        return usuariosRepository.findAll();
    }


    // ==========================
    // GUARDAR
    // ==========================

    @Override
    @Transactional
    public Usuarios guardar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }


    // ==========================
    // BUSCAR POR ID
    // ==========================

    @Override
    public Usuarios obtenerPorId(Long id) {
        return usuariosRepository
                .findById(id)
                .orElse(null);
    }


    // ==========================
    // CAMBIAR ESTADO
    // ==========================

    @Override
    @Transactional
    public void cambiarEstado(Long id, boolean activo) {

        Usuarios usuario = obtenerPorId(id);

        if (usuario != null) {

            usuario.setActivo(activo);

            usuariosRepository.save(usuario);
        }
    }


    // ==========================
    // AUTENTICAR
    // ==========================

    @Override
    public Usuarios autenticar(String correo, String clave) {

        Usuarios usuario = usuariosRepository
                .findByCorreo(correo)
                .orElse(null);

        if (usuario != null
                && usuario.getClave().equals(clave)
                && usuario.isActivo()) {

            return usuario;
        }

        return null;
    }


    // ==========================
    // EXISTE EMAIL
    // ==========================

    @Override
    public boolean existeEmail(String correo) {
        return usuariosRepository.existsByCorreo(correo);
    }


    // ==========================
    // EXISTE USUARIO
    // ==========================

    @Override
    public boolean existeUsername(String usuario) {
        return usuariosRepository.existsByUsuario(usuario);
    }


    // ==========================
    // CREAR O EDITAR
    // ==========================

    @Override
    @Transactional
    public Usuarios crearOeditar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }


    // ==========================
    // ELIMINAR
    // ==========================

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        usuariosRepository.deleteById(id);
    }
}