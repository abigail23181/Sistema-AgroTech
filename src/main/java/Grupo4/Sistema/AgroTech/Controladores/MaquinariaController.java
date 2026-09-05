package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/maquinaria", "/maquinarias"})
public class MaquinariaController {

    @Autowired
    private IMaquinariaService maquinariaService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("maquinarias", maquinariaService.listarTodas());
        return "maquinaria";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Maquinaria maquinaria, RedirectAttributes redirectAttrs) {
        maquinariaService.guardar(maquinaria);
        redirectAttrs.addFlashAttribute("mensaje", "Maquinaria agregada exitosamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/maquinaria";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Maquinaria maquinaria, RedirectAttributes redirectAttrs) {
        maquinariaService.guardar(maquinaria);
        redirectAttrs.addFlashAttribute("mensaje", "Maquinaria actualizada correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/maquinaria";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {
        maquinariaService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "Maquinaria eliminada correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
        return "redirect:/maquinaria";
    }
}