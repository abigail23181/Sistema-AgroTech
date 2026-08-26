package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IEmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final IEmpresaService empresaService;

    @Autowired
    public EmpresaController(IEmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public String listarEmpresas(Model model) {
        model.addAttribute("empresas", empresaService.obtenerTodas());
        return "empresas";
    }

    @PostMapping("/guardar")
    public String guardarEmpresa(@Valid @ModelAttribute("empresa") Empresa empresa,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("empresas", empresaService.obtenerTodas());
            return "empresas";
        }
        empresaService.guardar(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable("id") Long id) {
        empresaService.eliminar(id);
        return "redirect:/empresas";
    }
}