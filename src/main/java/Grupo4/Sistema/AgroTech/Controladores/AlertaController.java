package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private IAlertaService alertaService;

    @Autowired
    private MaquinariaRepository maquinariaRepository; // <-- 1. Inyectar el repositorio

    @GetMapping
    public String listarAlertas(@RequestParam(required = false) String tipo,
                                @RequestParam(required = false) String ubicacion,
                                @RequestParam(required = false) Long maquinariaId,
                                Model model) {

        List<Alerta> alertas = alertaService.obtenerAlertas(tipo, ubicacion, maquinariaId);

        model.addAttribute("alertas", alertas);
        model.addAttribute("maquinarias", maquinariaRepository.findAll()); // <-- 2. Pasar las maquinarias a la vista
        model.addAttribute("tipoFiltro", tipo);
        model.addAttribute("ubicacionFiltro", ubicacion);
        model.addAttribute("maquinariaFiltro", maquinariaId);

        return "alertas";
    }
}