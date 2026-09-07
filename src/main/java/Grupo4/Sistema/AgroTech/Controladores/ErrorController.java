package Grupo4.Sistema.AgroTech.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String accesoDenegado() {
        return "403";
    }
}