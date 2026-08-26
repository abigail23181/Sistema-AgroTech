package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Usuario;
import Grupo4.Sistema.AgroTech.Repositorios.UsuarioRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IUsuarioService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String email, String contraseña) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContraseña().equals(contraseña)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Page<Usuario> obtenerTodosPaginados(Pageable pegeable) {
        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return List.of();
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return null;
    }

    @Override
    public Usuario crearOeditar(Usuario usuario) {
        return null;
    }

    @Override
    public void eliminarPorId(Integer id) {

    }

    @Override
    public @Nullable Object listarTodos() {
        return null;
    }
}