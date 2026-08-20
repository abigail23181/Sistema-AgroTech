package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IEmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        return "empresas/index";
    }

    @GetMapping("/nuevo")
    public String formularioCrear(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresas/form";
    }

    @PostMapping("/guardar")
    public String guardarEmpresa(@Valid @ModelAttribute("empresa") Empresa empresa, 
                                 BindingResult result, 
                                 Model model) {
        if (result.hasErrors()) {
            return "empresas/form";
        }
        empresaService.guardar(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable("id") Long id, Model model) {
        Optional<Empresa> empresa = empresaService.obtenerPorId(id);
        if (empresa.isPresent()) {
            model.addAttribute("empresa", empresa.get());
            return "empresas/form";
        }
        return "redirect:/empresas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable("id") Long id) {
        empresaService.eliminar(id);
        return "redirect:/empresas";
    }
}