package Grupo4.Sistema.AgroTech.Controladores;

<<<<<<< Updated upstream
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import Grupo4.Sistema.AgroTech.Model.Incidencia;

=======
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Model.Alerta; // Asumiendo tu modelo de Alerta
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IncidenciaService;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAlertaService; // Servicio para CA06
import Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository;
>>>>>>> Stashed changes
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
>>>>>>> Stashed changes

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private MaquinariaRepository maquinariaRepository;

    @Autowired(required = false)
    private IAlertaService alertaService; // Para CA06: Generación automática de alerta

<<<<<<< Updated upstream
    // 1. Mostrar historial de incidencias
    @GetMapping("/historial")
    public String listarIncidencias(Model model) {
        model.addAttribute("incidencias", incidenciaService.listarTodas());
        return "incidencia_historial";
=======
    @GetMapping
    public String listarIncidencias(Model model) {
        model.addAttribute("incidencias", incidenciaService.obtenerTodas());
        model.addAttribute("maquinas", maquinariaRepository.findAll());
        if (!model.containsAttribute("incidencia")) {
            model.addAttribute("incidencia", new Incidencia());
        }
        return "incidencias";
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                                    BindingResult result,
                                    Model model) {
        // IMPORTANTE: BindingResult debe ir inmediatamente después del objeto con @Valid.
        if (result.hasErrors()) {
            return "incidencia_form";
        }

        incidenciaService.guardar(incidencia);
        return "redirect:/incidencias/historial";
=======
                                    BindingResult bindingResult,
                                    @RequestParam(value = "maquinaId", required = false) Long maquinaId,
                                    RedirectAttributes redirect,
                                    Model model) {

        // CA02: Validación de campos obligatorios
        if (bindingResult.hasErrors()) {
            redirect.addFlashAttribute("mensajeError", "Por favor complete todos los campos obligatorios correctamente.");
            return "redirect:/incidencias";
        }

        try {
            if (maquinaId != null) {
                Optional<Maquinaria> maqOpt = maquinariaRepository.findById(maquinaId);
                if (maqOpt.isPresent()) {
                    Maquinaria maquina = maqOpt.get();
                    incidencia.setMaquina(maquina);

                    // CA04: Captura automática de la ubicación de la máquina
                    if (maquina.getUbicacion() != null) {
                        incidencia.setUbicacion(maquina.getUbicacion());
                    }
                } else {
                    redirect.addFlashAttribute("mensajeError", "La máquina seleccionada no existe.");
                    return "redirect:/incidencias";
                }
            } else {
                redirect.addFlashAttribute("mensajeError", "Debe seleccionar una máquina.");
                return "redirect:/incidencias";
            }

            // CA08: Estado inicial predeterminado "PENDIENTE" (si es nueva)
            if (incidencia.getId() == null) {
                incidencia.setEstado("PENDIENTE");
                if (incidencia.getFechaHora() == null) {
                    incidencia.setFechaHora(LocalDateTime.now());
                }
            }

            // Guardar la incidencia
            Incidencia incidenciaGuardada = incidenciaService.registrarIncidencia(incidencia);

            // CA06: Generación automática de alerta correctiva para severidad MODERADA/MEDIA o CRÍTICA/ALTA
            if (incidenciaGuardada.getSeveridad() != null) {
                String sev = incidenciaGuardada.getSeveridad().toString().toUpperCase();
                if (sev.contains("MEDIA") || sev.contains("MODERADA") || sev.contains("CRITICA") || sev.contains("ALTA")) {
                    if (alertaService != null) {
                        alertaService.generarAlertaCorrectiva(incidenciaGuardada);
                    }
                }
            }

            redirect.addFlashAttribute("mensajeExito", "Incidencia registrada exitosamente.");
        } catch (Exception e) {
            redirect.addFlashAttribute("mensajeError", "Error al registrar la incidencia: " + e.getMessage());
        }
        return "redirect:/incidencias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarIncidencia(@PathVariable("id") Long id, RedirectAttributes redirect) {
        try {
            incidenciaService.eliminarPorId(id);
            redirect.addFlashAttribute("mensajeExito", "Incidencia eliminada correctamente.");
        } catch (Exception e) {
            redirect.addFlashAttribute("mensajeError", "Error al eliminar la incidencia.");
        }
        return "redirect:/incidencias";
>>>>>>> Stashed changes
    }
}