package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.ITipoMantenimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipos-mantenimiento")
public class TipoMantenimientoController {

    @Autowired
    private ITipoMantenimientoService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listarTodos());
        return "tipos-mantenimiento/index";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute TipoMantenimiento tipoMantenimiento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("lista", service.listarTodos());
            return "tipos-mantenimiento/index";
        }
        service.guardar(tipoMantenimiento);
        return "redirect:/tipos-mantenimiento";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id, @RequestParam Boolean activo) {
        service.cambiarEstado(id, activo);
        return "redirect:/tipos-mantenimiento";
    }
}