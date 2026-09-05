package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import Grupo4.Sistema.AgroTech.Repositorios.TipoMantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipomantenimiento")
public class TipoMantenimientoController {

    @Autowired
    private TipoMantenimientoRepository repository;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "tipomantenimiento";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute TipoMantenimiento tipoMantenimiento, RedirectAttributes redirectAttributes) {
        repository.save(tipoMantenimiento);
        redirectAttributes.addFlashAttribute("mensaje", "Tipo de mantenimiento guardado correctamente.");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Registro eliminado correctamente.");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }
}