package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.ITipoMantenimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipos-mantenimiento")
public class TipoMantenimientoController {

    @Autowired
    private ITipoMantenimientoService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listarTodos());
        return "tipos-mantenimiento/index";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute TipoMantenimiento tipoMantenimiento,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("lista", service.listarTodos());
            return "tipos-mantenimiento/index";
        }

        boolean esNuevo = (tipoMantenimiento.getId() == null);
        service.guardar(tipoMantenimiento);

        if (esNuevo) {
            redirectAttrs.addFlashAttribute("mensaje", "¡Guardado exitosamente!");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "¡Cambios Guardados Correctamente!");
        }
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");

        return "redirect:/tipos-mantenimiento";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id,
                                @RequestParam Boolean activo,
                                RedirectAttributes redirectAttrs) {
        service.cambiarEstado(id, activo);
        redirectAttrs.addFlashAttribute("mensaje", "¡Estado cambiado correctamente!");
        redirectAttrs.addFlashAttribute("tipoMensaje", "warning");
        return "redirect:/tipos-mantenimiento";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        service.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "¡Registro eliminado correctamente!");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
        return "redirect:/tipos-mantenimiento";
    }
}