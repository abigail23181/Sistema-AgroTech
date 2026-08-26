package Grupo4.Sistema.AgroTech.Servicios.Interfaces;
import Grupo4.Sistema.AgroTech.Model.Usuario;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

 Optional<Usuario> login(String email, String password);

 Page<Usuario> obtenerTodosPaginados(Pageable pegeable);

 List<Usuario> obtenerTodos();

 Usuario obtenerPorId(Integer id);

 Usuario crearOeditar(Usuario usuario);

 void eliminarPorId(Integer id);

 @Nullable Object listarTodos();
}