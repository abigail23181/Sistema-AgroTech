package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.ITipoMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipomantenimiento")
public class TipoMantenimientoController {

    @Autowired
    private ITipoMantenimientoService tipoMantenimientoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", tipoMantenimientoService.listarTodos());
        return "tipomantenimiento";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute TipoMantenimiento tipoMantenimiento, RedirectAttributes redirectAttrs) {
        tipoMantenimientoService.guardar(tipoMantenimiento);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento registrado exitosamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute TipoMantenimiento tipoMantenimiento, RedirectAttributes redirectAttrs) {
        tipoMantenimientoService.guardar(tipoMantenimiento);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento actualizado correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable("id") Long id, @RequestParam("activo") Boolean activo, RedirectAttributes redirectAttrs) {
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