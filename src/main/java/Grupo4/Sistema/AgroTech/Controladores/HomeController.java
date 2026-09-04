package Grupo4.Sistema.AgroTech.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

<<<<<<< Updated upstream
    @GetMapping({"/", "/home"})
    public String index() {
=======
    private final IEmpresaService empresaService;
    private final IMaquinariaService maquinariaService;
    private final IUsuariosService usuarioService;
    private final IAlertaService alertaService;
    private final IncidenciaService incidenciaService;

    public HomeController(IEmpresaService empresaService,
                          IMaquinariaService maquinariaService,
                          IUsuariosService usuarioService,
                          IAlertaService alertaService,
                          IncidenciaService incidenciaService) {
        this.empresaService = empresaService;
        this.maquinariaService = maquinariaService;
        this.usuarioService = usuarioService;
        this.alertaService = alertaService;
        this.incidenciaService = incidenciaService;
    }

    // Se cambia la ruta a "/home" para evitar duplicidad con AccesoController
    @GetMapping("/home")
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("empresas", empresaService.listarTodas());
        model.addAttribute("maquinarias", maquinariaService.listarTodas());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("alertas", alertaService.listarTodas());
        model.addAttribute("incidencias", incidenciaService.obtenerTodas());
>>>>>>> Stashed changes
        return "index";
    }
}