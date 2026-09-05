package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import Grupo4.Sistema.AgroTech.Repositorios.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaRepository alertaRepository;

    @GetMapping
    public String listarAlertas(Model model) {
        model.addAttribute("alertas", alertaRepository.findAll());
        return "alertas";
    }

    @PostMapping("/guardar")
    public String guardarAlerta(
            @RequestParam(value = "fechaLimite", required = false) String fechaLimite,
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "observaciones", required = false) String observaciones) {

        Alerta alerta = new Alerta();

        // Mapeo seguro de campos obligatorios
        alerta.setEstado((estado != null && !estado.isBlank()) ? estado : "Próxima");
        alerta.setObservaciones((observaciones != null) ? observaciones : "");

        if (fechaLimite != null && !fechaLimite.isBlank()) {
            alerta.setFechaLimite(LocalDate.parse(fechaLimite));
        } else {
            alerta.setFechaLimite(LocalDate.now());
        }

        alertaRepository.save(alerta);
        return "redirect:/alertas";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable("id") Long id, Model model) {
        Alerta alerta = alertaRepository.findById(id).orElse(null);
        model.addAttribute("alerta", alerta);
        return "alerta-detalle";
    }
}