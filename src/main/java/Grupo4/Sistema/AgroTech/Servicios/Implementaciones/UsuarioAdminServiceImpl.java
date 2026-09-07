package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.UsuarioAdmin;
import Grupo4.Sistema.AgroTech.Repositorios.UsuarioAdminRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IUsuarioAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioAdminServiceImpl implements IUsuarioAdminService {

    @Autowired
    private UsuarioAdminRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioAdmin> listarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioAdmin buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioAdmin buscarPorUsuario(String usuario) {
        return repository.findByUsuario(usuario).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(UsuarioAdmin usuario) {

        if (usuario.getUsuario() != null) {
            usuario.setUsuario(usuario.getUsuario().trim());
        }

        if (usuario.getEmail() != null) {
            usuario.setEmail(usuario.getEmail().trim());
        }

        if (usuario.getId() == null) {
            usuario.setActivo(true);
        }

        repository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void cambiarEstado(Long id) {

        UsuarioAdmin usuario = buscarPorId(id);

        if (usuario != null) {
            usuario.setActivo(!usuario.isActivo());
            repository.save(usuario);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean usuarioExiste(String usuario) {
        return repository.existsByUsuario(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean emailExiste(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean usuarioExisteEditando(String usuario, Long id) {
        return repository.existsByUsuarioAndIdNot(usuario, id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean emailExisteEditando(String email, Long id) {
        return repository.existsByEmailAndIdNot(email, id);
    }
}