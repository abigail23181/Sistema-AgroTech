package Grupo4.Sistema.AgroTech.Controladores;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccesoController {


    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpSession session) {

        if ("admin@agrotech.com".equals(username) && "123456".equals(password)) {
            session.setAttribute("usuarioLogueado", username);
            return "redirect:/dashboard";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}