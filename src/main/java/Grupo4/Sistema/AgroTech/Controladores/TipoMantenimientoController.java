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
    private ITipoMantenimientoService tipoMantenimientoService;


    @GetMapping
    public String listar(Model model) {
        if (!model.containsAttribute("tipoMantenimiento")) {
            model.addAttribute("tipoMantenimiento", new TipoMantenimiento());
        }
        model.addAttribute("lista", tipoMantenimientoService.listarTodos());
        return "tipomantenimiento";
    }

    // CA01, CA02, CA03, CA04: Guardar nuevo registro con validación
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("tipoMantenimiento") TipoMantenimiento tipoMantenimiento,
                          BindingResult result,
                          RedirectAttributes redirectAttrs) {


        if (tipoMantenimiento.getNombre() != null && !tipoMantenimiento.getNombre().isBlank()) {
            if (tipoMantenimientoService.existePorNombre(tipoMantenimiento.getNombre().trim())) {
                result.rejectValue("nombre", "error.nombre", "Ya existe un tipo de mantenimiento con este nombre.");
            }
        }


        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().get(0).getDefaultMessage();
            redirectAttrs.addFlashAttribute("mensaje", errorMsg);
            redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/tipomantenimiento";
        }

        tipoMantenimientoService.guardar(tipoMantenimiento);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento registrado exitosamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/editar")
    public String editar(@Valid @ModelAttribute("tipoMantenimiento") TipoMantenimiento tipoMantenimiento,
                         BindingResult result,
                         RedirectAttributes redirectAttrs) {

        if (tipoMantenimiento.getNombre() != null && !tipoMantenimiento.getNombre().isBlank()) {
            TipoMantenimiento existente = tipoMantenimientoService.obtenerPorId(tipoMantenimiento.getId());
            // Si cambió el nombre, se valida si el nuevo ya existe
            if (existente != null && !existente.getNombre().equalsIgnoreCase(tipoMantenimiento.getNombre().trim())) {
                if (tipoMantenimientoService.existePorNombre(tipoMantenimiento.getNombre().trim())) {
                    result.rejectValue("nombre", "error.nombre", "Ya existe un tipo de mantenimiento con este nombre.");
                }
            }
        }

        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().get(0).getDefaultMessage();
            redirectAttrs.addFlashAttribute("mensaje", errorMsg);
            redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/tipomantenimiento";
        }

        tipoMantenimientoService.guardar(tipoMantenimiento);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento actualizado correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable("id") Long id,
                                @RequestParam("activo") Boolean activo,
                                RedirectAttributes redirectAttrs) {
        TipoMantenimiento tm = tipoMantenimientoService.obtenerPorId(id);
        if (tm != null) {
            tm.setActivo(activo);
            tipoMantenimientoService.guardar(tm);
            redirectAttrs.addFlashAttribute("mensaje", "Estado actualizado correctamente");
            redirectAttrs.addFlashAttribute("tipoMensaje", "warning");
        }
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {
        tipoMantenimientoService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento eliminado correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
        return "redirect:/tipomantenimiento";
    }
}