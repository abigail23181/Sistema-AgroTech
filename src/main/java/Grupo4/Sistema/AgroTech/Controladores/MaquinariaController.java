package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
<<<<<<< HEAD
@RequestMapping({"/maquinaria", "/maquinarias"})
=======
@RequestMapping({"/maquinarias", "/maquinaria"})
>>>>>>> feature/HU-SCRUM-11
public class MaquinariaController {

    @Autowired
    private IMaquinariaService maquinariaService;

<<<<<<< HEAD
    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("maquinarias", maquinariaService.listarTodas());
        return "maquinaria";
=======
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", maquinariaService.listarTodas());
        model.addAttribute("maquinariaNueva", new Maquinaria());
        return "maquinaria"; // Busca el archivo src/main/resources/templates/maquinaria.html
>>>>>>> feature/HU-SCRUM-11
    }

    @PostMapping("/guardar")
    public String guardarMaquinaria(@ModelAttribute Maquinaria maquinaria, RedirectAttributes redirect) {
        try {
            if (maquinaria.getIdEmpresa() == null) {
                maquinaria.setIdEmpresa(1L);
            }

            if (maquinaria.getCodigoInterno() == null || maquinaria.getCodigoInterno().trim().isEmpty()) {
                String codigoAuto = "MAQ-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
                maquinaria.setCodigoInterno(codigoAuto);
            }

            maquinariaService.guardar(maquinaria);
            redirect.addFlashAttribute("mensaje", "Maquinaria guardada correctamente.");
            redirect.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirect.addFlashAttribute("mensaje", "Error al guardar la maquinaria: " + e.getMessage());
            redirect.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/maquinarias";
    }

    @PostMapping("/eliminar")
    public String eliminarMaquinaria(@RequestParam("id") Long id, RedirectAttributes redirect) {
        try {
            maquinariaService.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Maquinaria eliminada correctamente.");
            redirect.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirect.addFlashAttribute("mensaje", "Error al eliminar la maquinaria.");
            redirect.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/maquinarias";
    }
}