package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/incidencias", "/incidencia"}) // Acepta plural y singular
public class IncidenciaController {

    @Autowired
    private IIncidenciaService incidenciaService;

    // Acepta http://localhost:8080/incidencias y http://localhost:8080/incidencias/historial
    @GetMapping({"", "/", "/historial"})
    public String listarIncidencias(Model model) {
        model.addAttribute("incidencias", incidenciaService.listarTodas());
        return "incidencia_historial"; // Verifica que el archivo se llame tal cual en templates/
    }

    @PostMapping("/guardar")
    public String guardarIncidencia(@ModelAttribute Incidencia incidencia) {
        incidenciaService.guardar(incidencia);
        return "redirect:/incidencias";
    }

    @PostMapping("/editar")
    public String editarIncidencia(@ModelAttribute Incidencia incidencia) {
        incidenciaService.guardar(incidencia);
        return "redirect:/incidencias";
    }

    @PostMapping("/eliminar")
    public String eliminarIncidencia(@RequestParam("id") Long id) {
        incidenciaService.eliminar(id);
        return "redirect:/incidencias";
    }
}