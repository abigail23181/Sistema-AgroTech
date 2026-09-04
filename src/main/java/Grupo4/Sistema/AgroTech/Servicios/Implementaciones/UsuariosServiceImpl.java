package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Usuarios;
import Grupo4.Sistema.AgroTech.Repositorios.IUsuariosRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    @Override
    public List<Usuarios> listarTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios guardar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public Usuarios obtenerPorId(Long id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public void cambiarEstado(Long id, boolean activo) {
        Usuarios usuario = obtenerPorId(id);
        if (usuario != null) {
            usuario.setActivo(activo);
            usuariosRepository.save(usuario);
        }
    }

    @Override
    public Usuarios autenticar(String email, String password) {
        Usuarios usuario = usuariosRepository.findByEmail(email);
        // CA02: Bloquea el inicio de sesión si activo == false
        if (usuario != null && usuario.getPassword().equals(password) && usuario.isActivo()) {
            return usuario;
        }
        return null;
    }

    @Override
    public boolean existeEmail(String email) {
        return usuariosRepository.existsByEmail(email);
    }

    @Override
    public boolean existeUsername(String username) {
        return usuariosRepository.existsByUsername(username);
    }
}