package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Acceso;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IEmpresaService;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired(required = false)
    private IEmpresaService empresaService;

    @Autowired(required = false)
    private IMaquinariaService maquinariaService;

    @Autowired(required = false)
    private IIncidenciaService incidenciaService;

    @GetMapping({"/dashboard", "/home"})
    public String dashboard(HttpSession session, Model model) {
        Acceso acceso = (Acceso) session.getAttribute("accesoLogueado");

        // Si no se ha iniciado sesión en Acceso, redirige al login
        if (acceso == null) {
            return "redirect:/acceso";
        }

        // Carga de listas/contadores para el Dashboard (con validación de nulos)
        if (empresaService != null) {
            model.addAttribute("empresas", empresaService.listarTodas());
        }
        if (maquinariaService != null) {
            model.addAttribute("maquinarias", maquinariaService.listarTodas());
        }
        if (incidenciaService != null) {
            model.addAttribute("incidencias", incidenciaService.obtenerTodas());
        }

        return "dashboard";
    }
}