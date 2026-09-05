package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import Grupo4.Sistema.AgroTech.Model.Incidencia;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/incidencias")
public class IncidenciaController {

    @Autowired
    private IIncidenciaService incidenciaService;

    // 1. Mostrar historial de incidencias
    @GetMapping("/historial")
    public String listarIncidencias(Model model) {
        model.addAttribute("incidencias", incidenciaService.listarTodas());
        return "incidencia_historial";
    }

    // 2. Mostrar formulario para nueva incidencia
    @GetMapping("/nueva")
    public String formularioNuevaIncidencia(Model model) {
        model.addAttribute("incidencia", new Incidencia());
        return "incidencia_form";
    }

    // 3. Procesar el formulario de guardado
    @PostMapping("/guardar")
    public String guardarIncidencia(@Valid @ModelAttribute("incidencia") Incidencia incidencia,
                                    BindingResult result,
                                    Model model) {
        // IMPORTANTE: BindingResult debe ir inmediatamente después del objeto con @Valid.
        if (result.hasErrors()) {
            return "incidencia_form";
        }

        incidenciaService.guardar(incidencia);
        return "redirect:/incidencias/historial";
    }
}