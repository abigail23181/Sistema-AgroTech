package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Usuario;
import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> login(String email, String password);
}