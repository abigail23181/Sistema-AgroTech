package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/incidencia/historial")
public class IncidenciaController {

    @Autowired
    private IIncidenciaService incidenciaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("incidencias", incidenciaService.listarTodas());
        return "incidencia_historial";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Incidencia incidencia, RedirectAttributes redirectAttrs) {
        incidenciaService.guardar(incidencia);
        redirectAttrs.addFlashAttribute("mensaje", "Incidencia registrada exitosamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/incidencia/historial";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Incidencia incidencia, RedirectAttributes redirectAttrs) {
        incidenciaService.guardar(incidencia);
        redirectAttrs.addFlashAttribute("mensaje", "Incidencia actualizada correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/incidencia/historial";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {
        incidenciaService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "Incidencia eliminada correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
        return "redirect:/incidencia/historial";
    }
}