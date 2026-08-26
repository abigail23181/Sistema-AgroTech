package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IEmpresaService;
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

    @Autowired
    private IEmpresaService empresaService;

    @GetMapping
    public String listarMaquinarias(Model model) {
        model.addAttribute("maquinarias", maquinariaService.listarTodas());
        model.addAttribute("empresas", empresaService.listarTodas());
        return "maquinaria";
    }

    @PostMapping("/guardar")
    public String guardarMaquinaria(@ModelAttribute Maquinaria maquinaria, RedirectAttributes redirectAttrs) {
        maquinariaService.guardar(maquinaria);
        redirectAttrs.addFlashAttribute("mensaje", "¡Guardado exitosamente!");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/maquinaria";
    }

    @PostMapping("/editar")
    public String editarMaquinaria(@ModelAttribute Maquinaria maquinaria, RedirectAttributes redirectAttrs) {
        maquinariaService.guardar(maquinaria);
        redirectAttrs.addFlashAttribute("mensaje", "¡Maquinaria actualizada exitosamente!");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/maquinaria";
    }

    @PostMapping("/eliminar")
    public String eliminarMaquinaria(@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {
        maquinariaService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "¡Maquinaria eliminada exitosamente!");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
        return "redirect:/maquinaria";
    }
}