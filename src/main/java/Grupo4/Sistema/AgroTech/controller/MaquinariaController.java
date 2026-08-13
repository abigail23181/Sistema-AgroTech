package Grupo4.Sistema.AgroTech.controller;

import Grupo4.Sistema.AgroTech.model.Maquinaria;
import Grupo4.Sistema.AgroTech.service.MaquinariaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaquinariaController {

    private final MaquinariaService maquinariaService;

    public MaquinariaController(MaquinariaService maquinariaService) {
        this.maquinariaService = maquinariaService;
    }

    @GetMapping("/maquinarias")
    public String listarMaquinarias(Model model) {
        model.addAttribute("maquinarias", maquinariaService.obtenerTodos());
        return "maquinarias/listado";
    }
}