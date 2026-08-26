package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Servicios.Implementaciones.AlertaServiceImpl;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaServiceImpl alertaService;

    @Autowired
    private IMaquinariaService maquinariaService;

    // Listar alertas y filtrado
    @GetMapping
    public String listarAlertas(@RequestParam(required = false) String tipo,
                                @RequestParam(required = false) String ubicacion,
                                @RequestParam(required = false) Long maquinariaId,
                                Model model) {

        List<Alerta> alertas = alertaService.obtenerAlertas(tipo, ubicacion, maquinariaId);

        model.addAttribute("alertas", alertas);
        model.addAttribute("maquinarias", maquinariaService.listarTodas());
        model.addAttribute("tipoFiltro", tipo);
        model.addAttribute("ubicacionFiltro", ubicacion);
        model.addAttribute("maquinariaFiltro", maquinariaId);

        return "alertas";
    }

    // Crear Nueva Alerta
    @PostMapping("/guardar")
    public String guardarAlerta(@ModelAttribute Alerta alerta,
                                @RequestParam("maquinariaId") Long maquinariaId) {

        // Asociar la entidad Maquinaria seleccionada
        if (maquinariaId != null) {
            Optional<Maquinaria> maquinaria = maquinariaService.obtenerPorId(maquinariaId);
            alerta.setMaquinaria(maquinaria.orElse(null));
        }

        alertaService.guardar(alerta); // Ajusta según el nombre real de tu método
        return "redirect:/alertas";
    }

    // Editar Alerta Existente
    @PostMapping("/editar")
    public String editarAlerta(@ModelAttribute Alerta alerta,
                               @RequestParam("maquinariaId") Long maquinariaId) {

        if (maquinariaId != null) {
            Optional<Maquinaria> maquinaria = maquinariaService.obtenerPorId(maquinariaId);
            alerta.setMaquinaria(maquinaria.orElse(null));
        }

        alertaService.guardar(alerta); // O alertaService.actualizar(alerta);
        return "redirect:/alertas";
    }

    // Eliminar Alerta
    @PostMapping("/eliminar")
    public String eliminarAlerta(@RequestParam("id") Long id) {
        alertaService.eliminar(id); // Ajusta según el nombre real de tu método de servicio
        return "redirect:/alertas";
    }
}