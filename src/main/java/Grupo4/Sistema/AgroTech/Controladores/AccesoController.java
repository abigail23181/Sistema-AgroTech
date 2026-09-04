package Grupo4.Sistema.AgroTech.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AccesoController {

    @GetMapping("/login")
<<<<<<< Updated upstream
    public String login() {
        return "login"; // Carga templates/login.html
=======
    public String login(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpSession session) {

        boolean correoValido = username.endsWith("@agrotech.com") || username.endsWith("@gmail.com");

        if (correoValido && "123456".equals(password)) {
            session.setAttribute("usuarioLogueado", username);
            session.setAttribute("rolLogueado", "Administrador"); // Asigna el rol correspondiente
            return "redirect:/dashboard";
        }

        // Falla de autenticación genérica por seguridad (criterio 2)
        return "redirect:/login?error=true";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        // Módulos exigidos en la historia de usuario
        List<String> modulosAutorizados = Arrays.asList(
                "Catálogo e inventario de maquinaria industrial",
                "Programación y calendario de mantenimiento preventivo",
                "Alertas y registro de ordenes de trabajo por imprevistos/correctivos",
                "Gestión de usuarios y roles (técnicos, supervisores, operadores)"
        );

        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        model.addAttribute("rol", session.getAttribute("rolLogueado"));
        model.addAttribute("modulos", modulosAutorizados);

        return "dashboard";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam("nombre") String nombre,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        if (!username.endsWith("@agrotech.com") && !username.endsWith("@gmail.com")) {
            return "redirect:/registro?errorCorreo=true";
        }
        return "redirect:/login?exito=Cuenta+creada+exitosamente";
    }

    @GetMapping("/recuperar")
    public String mostrarRecuperar() {
        return "recuperar";
    }

    @PostMapping("/recuperar")
    public String procesarRecuperar(@RequestParam("username") String username) {
        if (!username.endsWith("@agrotech.com") && !username.endsWith("@gmail.com")) {
            return "redirect:/recuperar?errorCorreo=true";
        }
        return "redirect:/login?exito=Enlace+enviado+a+tu+correo";
    }

    @GetMapping("/perfil")
    public String verPerfil(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", session.getAttribute("usuarioLogueado"));
        return "perfil";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
>>>>>>> Stashed changes
    }
}