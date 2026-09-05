package Grupo4.Sistema.AgroTech.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccesoController {

    @GetMapping("/login")
    public String login() {
        return "login";// Carga templates/login.html
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @GetMapping("/recuperar")
    public String recuperar() {
        return "recuperar";
    }


    @GetMapping("/perfil")
    public String perfil() { return "perfil"; }
}