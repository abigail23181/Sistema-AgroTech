package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
<<<<<<< HEAD
import Grupo4.Sistema.AgroTech.Repositorios.TipoMantenimientoRepository;
=======
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.ITipoMantenimientoService;
import jakarta.validation.Valid;
>>>>>>> feature/HU-SCRUM-11
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipomantenimiento")
public class TipoMantenimientoController {

    @Autowired
    private TipoMantenimientoRepository repository;

<<<<<<< HEAD
    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("lista", repository.findAll());
=======

    @GetMapping
    public String listar(Model model) {
        if (!model.containsAttribute("tipoMantenimiento")) {
            model.addAttribute("tipoMantenimiento", new TipoMantenimiento());
        }
        model.addAttribute("lista", tipoMantenimientoService.listarTodos());
>>>>>>> feature/HU-SCRUM-11
        return "tipomantenimiento";
    }

    @PostMapping("/guardar")
<<<<<<< HEAD
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
=======
    public String guardar(@Valid @ModelAttribute("tipoMantenimiento") TipoMantenimiento tipoMantenimiento,
                          BindingResult result,
                          RedirectAttributes redirectAttrs) {


        if (tipoMantenimiento.getNombre() != null && !tipoMantenimiento.getNombre().isBlank()) {
            if (tipoMantenimientoService.existePorNombre(tipoMantenimiento.getNombre().trim())) {
                result.rejectValue("nombre", "error.nombre", "Ya existe un tipo de mantenimiento con este nombre.");
            }
        }


        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().get(0).getDefaultMessage();
            redirectAttrs.addFlashAttribute("mensaje", errorMsg);
            redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/tipomantenimiento";
        }

        tipoMantenimientoService.guardar(tipoMantenimiento);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento registrado exitosamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/editar")
    public String editar(@Valid @ModelAttribute("tipoMantenimiento") TipoMantenimiento tipoMantenimiento,
                         BindingResult result,
                         RedirectAttributes redirectAttrs) {

        if (tipoMantenimiento.getNombre() != null && !tipoMantenimiento.getNombre().isBlank()) {
            TipoMantenimiento existente = tipoMantenimientoService.obtenerPorId(tipoMantenimiento.getId());

            if (existente != null && !existente.getNombre().equalsIgnoreCase(tipoMantenimiento.getNombre().trim())) {
                if (tipoMantenimientoService.existePorNombre(tipoMantenimiento.getNombre().trim())) {
                    result.rejectValue("nombre", "error.nombre", "Ya existe un tipo de mantenimiento con este nombre.");
                }
            }
        }

        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().get(0).getDefaultMessage();
            redirectAttrs.addFlashAttribute("mensaje", errorMsg);
            redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/tipomantenimiento";
        }

        tipoMantenimientoService.guardar(tipoMantenimiento);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento actualizado correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable("id") Long id,
                                @RequestParam("activo") Boolean activo,
                                RedirectAttributes redirectAttrs) {
        TipoMantenimiento tm = tipoMantenimientoService.obtenerPorId(id);
        if (tm != null) {
            tm.setActivo(activo);
            tipoMantenimientoService.guardar(tm);
            redirectAttrs.addFlashAttribute("mensaje", "Estado actualizado correctamente");
            redirectAttrs.addFlashAttribute("tipoMensaje", "warning");
        }
        return "redirect:/tipomantenimiento";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {
        tipoMantenimientoService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "Tipo de mantenimiento eliminado correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
>>>>>>> feature/HU-SCRUM-11
        return "redirect:/tipomantenimiento";
    }
}