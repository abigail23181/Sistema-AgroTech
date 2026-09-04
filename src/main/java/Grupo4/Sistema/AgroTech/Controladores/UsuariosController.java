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
    private IUsuariosService usuariosService;

    // CA03: Verificación de Rol Administrativo
    private boolean esAdmin(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        return "ADMIN".equalsIgnoreCase(rol);
    }

    @GetMapping
    public String listarUsuarios(HttpSession session, Model model) {
        if (!esAdmin(session)) {
            return "error/403"; // CA03: 403 Forbidden
        }

        model.addAttribute("usuarios", usuariosService.listarTodos());
        // SOLUCIÓN: Instancia obligatoria para bindear el formulario del modal
        model.addAttribute("usuario", new Usuarios());

        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(HttpSession session, Model model) {
        if (!esAdmin(session)) {
            return "error/403";
        }
        model.addAttribute("usuario", new Usuarios());
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuarios usuario, HttpSession session, RedirectAttributes flash) {
        if (!esAdmin(session)) {
            return "error/403";
        }

        // CA04: Validación de Usuario o Correo Duplicado
        if (usuario.getId() == null) {
            if (usuariosService.existeEmail(usuario.getEmail())) {
                flash.addFlashAttribute("error", "El correo electrónico ya se encuentra registrado.");
                return "redirect:/usuarios";
            }
            if (usuariosService.existeUsername(usuario.getUsername())) {
                flash.addFlashAttribute("error", "El nombre de usuario ya se encuentra registrado.");
                return "redirect:/usuarios";
            }
        }

        usuariosService.guardar(usuario);
        flash.addFlashAttribute("exito", "Usuario registrado exitosamente"); // CA01
        return "redirect:/usuarios";
    }

    // CA02: Cambio de Estado (Desactivar/Activar)
    @GetMapping("/estado/{id}/{estado}")
    public String cambiarEstado(@PathVariable Long id, @PathVariable boolean estado, HttpSession session, RedirectAttributes flash) {
        if (!esAdmin(session)) {
            return "error/403";
        }
        usuariosService.cambiarEstado(id, estado);
        flash.addFlashAttribute("exito", "Estado del usuario actualizado correctamente.");
        return "redirect:/usuarios";
    }
}