package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Usuarios;
import Grupo4.Sistema.AgroTech.Repositorios.IUsuariosRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImpl implements IUsuariosService { // <-- Se eliminó "abstract"

    @Autowired
    private IUsuariosRepository usuarioRepository;

    @Override
    public List<Usuarios> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuarios guardarUsuario(IUsuariosService usuario) {
        return null;
    }

    @Override
    public Usuarios guardarUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuarios obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void cambiarEstado(Long id, boolean activo) {
        Usuarios user = obtenerPorId(id);
        if (user != null) {
            user.setActivo(activo);
            usuarioRepository.save(user);
        }
    }

    @Override
    public boolean existeUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public void guardarUsuarios(Usuarios usuario) {

    }
}