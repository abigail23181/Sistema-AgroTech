package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import Grupo4.Sistema.AgroTech.Servicio.Interfaces.IEmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

    private boolean esAdministrador(String rolActual) {
        return "ADMIN".equalsIgnoreCase(rolActual);
    }

    @GetMapping
    public String listarEmpresas(@RequestParam(value = "rolSesion", defaultValue = "ADMIN") String rolSesion,
                                 Model model) {
        if (!esAdministrador(rolSesion)) {
            return "redirect:/empresas/error-403";
        }
        model.addAttribute("empresas", empresaService.listarTodas());
        model.addAttribute("empresa", new Empresa());
        return "empresas";
    }

    @PostMapping("/guardar")
    public String guardarEmpresa(@Valid @ModelAttribute("empresa") Empresa empresa,
                                 BindingResult result,
                                 @RequestParam(value = "rolSesion", defaultValue = "ADMIN") String rolSesion,
                                 Model model,
                                 RedirectAttributes redirect) {

        if (!esAdministrador(rolSesion)) {
            return "redirect:/empresas/error-403";
        }

        if (empresaService.existeCorreo(empresa.getCorreo(), empresa.getId())) {
            result.rejectValue("correo", "error.empresa", "Este correo ya se encuentra registrado.");
        }
        if (empresaService.existeRuc(empresa.getRuc(), empresa.getId())) {
            result.rejectValue("ruc", "error.empresa", "Este RUC/NIT ya se encuentra registrado.");
        }

        if (result.hasErrors()) {
            model.addAttribute("empresas", empresaService.listarTodas());
            return "empresas";
        }

        try {
            if (empresa.getId() == null) {
                empresaService.guardar(empresa);
                redirect.addFlashAttribute("mensajeExito", "Empresa registrada con éxito.");
            } else {
                empresaService.actualizar(empresa.getId(), empresa);
                redirect.addFlashAttribute("mensajeExito", "Empresa actualizada con éxito.");
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("mensajeError", e.getMessage());
        }

        return "redirect:/empresas";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Long id,
                                @RequestParam Boolean estado,
                                @RequestParam(value = "rolSesion", defaultValue = "ADMIN") String rolSesion,
                                RedirectAttributes redirect) {

        if (!esAdministrador(rolSesion)) {
            return "redirect:/empresas/error-403";
        }

        empresaService.cambiarEstado(id, estado);
        redirect.addFlashAttribute("mensajeExito", "Estado de la empresa actualizado correctamente.");
        return "redirect:/empresas";
    }

    @GetMapping("/error-403")
    public String error403() {
        return "error/403";
    }
}