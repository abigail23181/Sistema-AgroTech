package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAlertaService;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private IAlertaService alertaService;

    @Autowired
    private IMaquinariaService maquinariaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alertas", alertaService.listarTodas());
        model.addAttribute("maquinarias", maquinariaService.listarTodas());
        return "alertas"; // Nombre del archivo HTML
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Alerta alerta,
                          @RequestParam("maquinariaId") Long maquinariaId,
                          RedirectAttributes redirectAttrs) {
        Maquinaria m = new Maquinaria();
        m.setIdMaquinaria(maquinariaId);
        alerta.setMaquinaria(m);

        alertaService.guardar(alerta);
        redirectAttrs.addFlashAttribute("mensaje", "Alerta creada exitosamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/alertas";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Alerta alerta,
                         @RequestParam("maquinariaId") Long maquinariaId,
                         RedirectAttributes redirectAttrs) {
        Maquinaria m = new Maquinaria();
        m.setIdMaquinaria(maquinariaId);
        alerta.setMaquinaria(m);

        alertaService.guardar(alerta);
        redirectAttrs.addFlashAttribute("mensaje", "Alerta actualizada correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/alertas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {
        alertaService.eliminar(id);
        redirectAttrs.addFlashAttribute("mensaje", "Alerta eliminada correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "danger");
        return "redirect:/alertas";
    }
}