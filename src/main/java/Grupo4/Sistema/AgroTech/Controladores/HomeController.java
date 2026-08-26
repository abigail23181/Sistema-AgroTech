package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Servicios.Interfaces.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final IEmpresaService empresaService;
    private final IMaquinariaService maquinariaService;
    private final IUsuarioService usuarioService;
    private final IAlertaService alertaService;
    private final IIncidenciaService incidenciaService;

    public HomeController(IEmpresaService empresaService,
                          IMaquinariaService maquinariaService,
                          IUsuarioService usuarioService,
                          IAlertaService alertaService,
                          IIncidenciaService incidenciaService) {
        this.empresaService = empresaService;
        this.maquinariaService = maquinariaService;
        this.usuarioService = usuarioService;
        this.alertaService = alertaService;
        this.incidenciaService = incidenciaService;
    }

    @GetMapping("/dashboard")
    public String index(Model model) {
        model.addAttribute("empresas", empresaService.listarTodas());
        model.addAttribute("maquinarias", maquinariaService.listarTodas());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("alertas", alertaService.listarTodas());
        model.addAttribute("incidencias", incidenciaService.listarTodas());
        return "index";
    }
}