package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empresas", empresaService.listarTodas());
        return "empresas"; // Nombre de tu plantilla Thymeleaf
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Empresa empresa, RedirectAttributes redirectAttrs) {
        boolean esNueva = (empresa.getIdEmpresa() == null);
        empresaService.guardar(empresa);

        if (esNueva) {
            redirectAttrs.addFlashAttribute("mensaje", "Empresa registrada exitosamente");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "Empresa actualizada correctamente");
        }
        return "redirect:/empresas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        empresaService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "Empresa eliminada correctamente");
        return "redirect:/empresas";
    }
}