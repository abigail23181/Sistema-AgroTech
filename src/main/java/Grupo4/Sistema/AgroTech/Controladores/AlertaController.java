package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAlertaService;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
@Controller
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private IAlertaService alertaService;

    @Autowired
    private IMaquinariaService maquinariaService;


    
    @GetMapping
    public String listarAlertas(Model model) {

        model.addAttribute(
                "alertas",
                alertaService.listarTodas()
        );

        model.addAttribute(
                "maquinarias",
                maquinariaService.listarTodas()
        );

        return "/alertas";
    }


    @PostMapping("/guardar")
    public String guardarAlerta(
            @ModelAttribute Alerta alerta,
            @RequestParam("idMaquinaria") Long idMaquinaria,
            RedirectAttributes attribute) {

        Maquinaria maquinaria = maquinariaService
                .buscarPorId(idMaquinaria)
                .orElse(null);

        if (maquinaria == null) {

            attribute.addFlashAttribute(
                    "mensaje",
                    "La maquinaria seleccionada no existe."
            );

            attribute.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );

            return "redirect:/alertas";
        }

        alerta.setMaquinaria(maquinaria);

        alertaService.guardar(alerta);

        attribute.addFlashAttribute(
                "mensaje",
                "¡Alerta registrada exitosamente!"
        );

        attribute.addFlashAttribute(
                "tipoMensaje",
                "success"
        );

        return "redirect:/alertas";
    }


    @PostMapping("/editar")
    public String editarAlerta(
            @ModelAttribute Alerta alerta,
            @RequestParam("idMaquinaria") Long idMaquinaria,
            RedirectAttributes attribute) {

        Maquinaria maquinaria = maquinariaService
                .buscarPorId(idMaquinaria)
                .orElse(null);

        if (maquinaria == null) {

            attribute.addFlashAttribute(
                    "mensaje",
                    "La maquinaria seleccionada no existe."
            );

            attribute.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );

            return "redirect:/alertas";
        }

        alerta.setMaquinaria(maquinaria);

        alertaService.guardar(alerta);

        attribute.addFlashAttribute(
                "mensaje",
                "¡Alerta actualizada correctamente!"
        );

        attribute.addFlashAttribute(
                "tipoMensaje",
                "success"
        );

        return "redirect:/alertas";
    }

    @PostMapping("/eliminar")
    public String eliminarAlerta(
            @RequestParam("id") Long id,
            RedirectAttributes attribute) {

        alertaService.eliminarPorId(id);

        attribute.addFlashAttribute(
                "mensaje",
                "¡Alerta eliminada correctamente!"
        );

        attribute.addFlashAttribute(
                "tipoMensaje",
                "warning"
        );

        return "redirect:/alertas";
    }
}

