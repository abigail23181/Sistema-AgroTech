package Grupo4.Sistema.AgroTech.Controladores;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccesoController {

    @GetMapping("/")
    public String inicio(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
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
            return "redirect:/dashboard";
        }

        return "redirect:/login?error=true";
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
    }
}