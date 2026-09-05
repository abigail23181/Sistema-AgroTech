package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import Grupo4.Sistema.AgroTech.Repositorios.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public String verPerfil(Model model) {
        List<Empresa> lista = empresaRepository.findAll();
        Empresa empresa = lista.isEmpty() ? new Empresa() : lista.get(0);
        model.addAttribute("empresa", empresa);
        return "empresas";
    }

    @PostMapping("/guardar")
    public String guardarPerfil(
            @ModelAttribute("empresa") Empresa empresa,
            @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
            RedirectAttributes redirectAttributes) {

        // Restricción a registro único: busca el registro maestro existente
        List<Empresa> existentes = empresaRepository.findAll();
        if (!existentes.isEmpty()) {
            Empresa maestro = existentes.get(0);
            empresa.setId(maestro.getId());
            if (logoFile == null || logoFile.isEmpty()) {
                empresa.setLogoUrl(maestro.getLogoUrl());
            }
        }

        // Carga y almacenamiento del Logotipo Institucional
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                String nombreArchivo = System.currentTimeMillis() + "_" + logoFile.getOriginalFilename();
                Path rutaUpload = Paths.get("uploads");
                if (!Files.exists(rutaUpload)) {
                    Files.createDirectories(rutaUpload);
                }
                Path rutaCompleta = rutaUpload.resolve(nombreArchivo);
                Files.copy(logoFile.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

                empresa.setLogoUrl("/uploads/" + nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        empresaRepository.save(empresa);
        redirectAttributes.addFlashAttribute("mensajeExito", "Datos de la empresa actualizados correctamente");
        return "redirect:/empresas";
    }
}