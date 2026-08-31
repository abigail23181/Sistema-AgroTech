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
@RequestMapping("/tipomantenimiento")
public class TipoMantenimientoController {

    @Autowired
    private ITipoMantenimientoService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listarTodos());
        // Instancia vacía para que Thymeleaf procese el formulario correctamente
        if (!model.containsAttribute("tipoMantenimiento")) {
            model.addAttribute("tipoMantenimiento", new TipoMantenimiento());
        }
        return "tipomantenimiento";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("tipoMantenimiento") TipoMantenimiento tipoMantenimiento,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttrs) {

        // Imprimir errores en consola si falla la validación
        if (result.hasErrors()) {
            System.out.println(">>> ERRORES AL GUARDAR:");
            result.getAllErrors().forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("lista", service.listarTodos());
            return "tipomantenimiento";
        }

        boolean esNuevo = (tipoMantenimiento.getId() == null);
        service.guardar(tipoMantenimiento);

        if (esNuevo) {
            redirectAttrs.addFlashAttribute("mensaje", "¡Guardado exitosamente!");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "¡Cambios Guardados Correctamente!");
        }
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");

        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id,
                                @RequestParam Boolean activo,
                                RedirectAttributes redirectAttrs) {
        service.cambiarEstado(id, activo);
        redirectAttrs.addFlashAttribute("mensaje", "¡Estado cambiado correctamente!");
        redirectAttrs.addFlashAttribute("tipoMensaje", "warning");
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        service.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "¡Registro eliminado correctamente!");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
        return "redirect:/tipomantenimiento";
    }
}