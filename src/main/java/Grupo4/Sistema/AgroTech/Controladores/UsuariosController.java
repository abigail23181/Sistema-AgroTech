package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Usuarios;
import Grupo4.Sistema.AgroTech.Model.Usuarios;
import Grupo4.Sistema.AgroTech.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("usuarios", repository.findAll());
        return "usuarios";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuarios usuario, RedirectAttributes redirectAttributes) {
        if (usuario.getId() != null) {
            Optional<Usuarios> usuarioExistente = repository.findById(usuario.getId());
            if (usuarioExistente.isPresent()) {
                // Si la clave viene vacía al editar, conserva la clave que ya tenía
                if (usuario.getClave() == null || usuario.getClave().trim().isEmpty()) {
                    usuario.setClave(usuarioExistente.get().getClave());
                }
            }
        }
        repository.save(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario guardado exitosamente.");
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado correctamente.");
        return "redirect:/usuarios";
    }
}