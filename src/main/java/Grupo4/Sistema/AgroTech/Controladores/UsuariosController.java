package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Usuarios;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IUsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService usuarioService;

    // Métodos aux. para RBAC (CA03)
    private boolean estaAutenticado(HttpSession session) {
        return session.getAttribute("usuarioLogueado") != null;
    }

    private boolean esAdmin(HttpSession session) {
        String rol = (String) session.getAttribute("rolLogueado");
        return "Administrador".equalsIgnoreCase(rol);
    }

    // Listar usuarios en el módulo (Solo Administrador)
    @GetMapping
    public String listarUsuarios(HttpSession session, Model model) {
        if (!estaAutenticado(session)) {
            return "redirect:/login";
        }

        // CA03: Bloqueo RBAC si no es Administrador
        if (!esAdmin(session)) {
            return "error/403";
        }

        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("nuevoUsuario", new Usuarios());
        return "usuarios";
    }

    // Guardar / Crear / Editar Usuario (CA01, CA02, CA04)
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("nuevoUsuario") Usuarios usuario,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        if (!estaAutenticado(session)) {
            return "redirect:/login";
        }

        if (!esAdmin(session)) {
            return "error/403";
        }

        // CA04: Validación de Nombre de Usuario o Correo Duplicado (para registros nuevos)
        if (usuario.getId() == null) {
            boolean usernameExiste = usuarioService.existeUsername(usuario.getUsername());
            boolean correoExiste = usuarioService.existeCorreo(usuario.getCorreo());

            if (usernameExiste || correoExiste) {
                redirectAttributes.addFlashAttribute("errorDuplicado",
                        "El nombre de usuario o correo electrónico ya se encuentra registrado.");
                return "redirect:/usuarios";
            }
        }

        // CA01: Guardar la cuenta y habilitar accesos según el rol
        usuarioService.guardarUsuario(usuario);

        // Mensaje requerido en CA01
        redirectAttributes.addFlashAttribute("exito", "Usuario registrado exitosamente");
        return "redirect:/usuarios";
    }

    // CA02: Cambio de Estado (Activar / Desactivar Usuario)
    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable("id") Long id,
                                @RequestParam("activo") boolean activo,
                                HttpSession session) {
        if (!estaAutenticado(session)) {
            return "redirect:/login";
        }

        if (!esAdmin(session)) {
            return "error/403";
        }

        usuarioService.cambiarEstado(id, activo);
        return "redirect:/usuarios";
    }
}