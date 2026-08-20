package Grupo4.Sistema.AgroTech.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaquinariaController {

    @GetMapping("/maquinarias")
    public String listarMaquinarias() {
        return "maquinaria";
    }
}