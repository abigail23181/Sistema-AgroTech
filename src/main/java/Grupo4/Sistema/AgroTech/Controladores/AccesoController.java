package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Acceso;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAccesoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AccesoController {

    @Autowired
    private IAccesoService accesoService;

    @GetMapping({"/", "/acceso", "/login"})
    public String verAcceso() {
        return "acceso";
    }

    @PostMapping("/acceso")
    public String procesarAcceso(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 HttpSession session,
                                 Model model) {

        // 1. CREDENCIAI FJA POR DEFECTO PARA PRUEBAS
        if ("admin@agrotech.com".equalsIgnoreCase(email) && "123456".equals(password)) {
            Acceso usuarioAdmin = new Acceso();
            usuarioAdmin.setEmail(email);
            usuarioAdmin.setNombre("Administrador");
            usuarioAdmin.setRol("ADMIN");

            session.setAttribute("accesoLogueado", usuarioAdmin);
            session.setAttribute("rol", usuarioAdmin.getRol());

            return "redirect:/dashboard";
        }

        // 2. BUSQUEDA NORMAL EN LA BASE DE DATOS (SI NO ES EL ADMIN POR DEFECTO)
        Optional<Acceso> accesoOpt = Optional.ofNullable(accesoService.autenticar(email, password));

        if (accesoOpt.isPresent()) {
            Acceso acceso = accesoOpt.get();
            session.setAttribute("accesoLogueado", acceso);
            session.setAttribute("rol", acceso.getRol());

            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "acceso";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/acceso";
    }
}