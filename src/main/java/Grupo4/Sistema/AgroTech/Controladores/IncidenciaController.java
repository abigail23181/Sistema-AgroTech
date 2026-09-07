package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/incidencia/historial")
public class IncidenciaController {

    @Autowired
    private IIncidenciaService incidenciaService;

    @Autowired
    private Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository maquinariaRepository;


    // MOSTRAR TODAS LAS INCIDENCIAS
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "incidencias",
                incidenciaService.listarTodas()
        );

        // ENVIAR LAS MAQUINARIAS A LA VISTA
        List<Maquinaria> maquinarias = maquinariaRepository.findAll();

        model.addAttribute("maquinarias", maquinarias);

        return "incidencia_historial";
    }


    // CREAR
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Incidencia incidencia,
            @RequestParam("maquinaId") Long maquinaId,
            RedirectAttributes redirectAttrs) {

        Maquinaria maquinaria = maquinariaRepository
                .findById(maquinaId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Maquinaria no encontrada")
                );

        incidencia.setMaquinaria(maquinaria);

        incidenciaService.guardar(incidencia);

        redirectAttrs.addFlashAttribute(
                "mensaje",
                "Incidencia registrada exitosamente"
        );

        redirectAttrs.addFlashAttribute(
                "tipoMensaje",
                "success"
        );

        return "redirect:/incidencia/historial";
    }


    // EDITAR
    @PostMapping("/editar")
    public String editar(
            @ModelAttribute Incidencia incidencia,
            @RequestParam("maquinaId") Long maquinaId,
            RedirectAttributes redirectAttrs) {

        Maquinaria maquinaria = maquinariaRepository
                .findById(maquinaId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Maquinaria no encontrada")
                );

        incidencia.setMaquinaria(maquinaria);

        incidenciaService.guardar(incidencia);

        redirectAttrs.addFlashAttribute(
                "mensaje",
                "Incidencia actualizada correctamente"
        );

        redirectAttrs.addFlashAttribute(
                "tipoMensaje",
                "success"
        );

        return "redirect:/incidencia/historial";
    }


    // ELIMINAR
    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttrs) {

        try {

            incidenciaService.eliminar(id);

            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "Incidencia eliminada correctamente"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );

        } catch (Exception e) {

            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "No se pudo eliminar la incidencia"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );
        }

        return "redirect:/incidencia/historial";
    }
}