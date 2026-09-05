package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository; // Importa tu repositorio o servicio de Maquinarias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping({"/incidencias", "/incidencia"})
public class IncidenciaController {

    @Autowired
    private IIncidenciaService incidenciaService;

    @Autowired
    private MaquinariaRepository maquinariaRepository; // Reemplaza si usas servicio (ej. IMaquinariaService)

    @GetMapping({"", "/"})
    public String listarIncidencias(Model model) {
        model.addAttribute("incidencias", incidenciaService.listarTodas());
        model.addAttribute("maquinarias", maquinariaRepository.findAll()); // Pasa las máquinas al formulario
        return "incidencias";
    }

    @PostMapping("/guardar")
    public String guardarIncidencia(@ModelAttribute("incidencia") Incidencia incidencia, RedirectAttributes redirectAttributes) {
        if (incidencia.getFechaHora() == null) {
            incidencia.setFechaHora(LocalDateTime.now());
        }
        if (incidencia.getEstado() == null || incidencia.getEstado().isEmpty()) {
            incidencia.setEstado("PENDIENTE");
        }

        incidenciaService.guardar(incidencia);
        redirectAttributes.addFlashAttribute("mensaje", "Incidencia procesada con éxito.");
        return "redirect:/incidencias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarIncidencia(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        incidenciaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Incidencia eliminada correctamente.");
        return "redirect:/incidencias";
    }
}