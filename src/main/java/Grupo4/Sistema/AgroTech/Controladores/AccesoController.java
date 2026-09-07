package Grupo4.Sistema.AgroTech.Controladores;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccesoController {

<<<<<<< HEAD
    @GetMapping("/login")
    public String login() {
        return "login";// Carga templates/login.html
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
=======
    // ==============================
    // DATOS DEL ADMINISTRADOR
    // ==============================

    private static final String CORREO_ADMIN =
            "admin@agrotech.com";

    private static final String PASSWORD_ADMIN =
            "123456";


    // ==============================
    // INICIO
    // ==============================

    @GetMapping("/")
    public String inicio(HttpSession session) {

        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/dashboard";
        }

        return "redirect:/login";
    }


    // ==============================
    // MOSTRAR LOGIN
    // ==============================

    @GetMapping("/login")
    public String login(HttpSession session) {

        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/dashboard";
        }

        return "login";
    }


    // ==============================
    // PROCESAR LOGIN
    // ==============================

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        username = username.trim();

        if (CORREO_ADMIN.equalsIgnoreCase(username)
                && PASSWORD_ADMIN.equals(password)) {

            session.setAttribute(
                    "usuarioLogueado",
                    CORREO_ADMIN
            );

            session.setAttribute(
                    "rol",
                    "ADMINISTRADOR"
            );

            return "redirect:/dashboard";
        }

        return "redirect:/login?error=true";
>>>>>>> feature/HU-SCRUM-11
    }


    // ==============================
    // REGISTRO
    // ==============================

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

<<<<<<< HEAD
=======

    @PostMapping("/registro")
    public String procesarRegistro(
            @RequestParam("nombre") String nombre,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        if (!username.endsWith("@agrotech.com")
                && !username.endsWith("@gmail.com")) {

            return "redirect:/registro?errorCorreo=true";
        }

        return "redirect:/login?exito=Cuenta+creada+exitosamente";
    }


    // ==============================
    // RECUPERAR
    // ==============================

>>>>>>> feature/HU-SCRUM-11
    @GetMapping("/recuperar")
    public String recuperar() {
        return "recuperar";
    }

<<<<<<< HEAD
=======

    @PostMapping("/recuperar")
    public String procesarRecuperar(
            @RequestParam("username") String username) {

        if (!username.endsWith("@agrotech.com")
                && !username.endsWith("@gmail.com")) {

            return "redirect:/recuperar?errorCorreo=true";
        }

        return "redirect:/login?exito=Enlace+enviado+a+tu+correo";
    }
>>>>>>> feature/HU-SCRUM-11


    // ==============================
    // PERFIL
    // ==============================

    @GetMapping("/perfil")
<<<<<<< HEAD
    public String perfil() { return "perfil"; }
=======
    public String verPerfil(
            HttpSession session,
            Model model) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "usuario",
                session.getAttribute("usuarioLogueado")
        );

        return "perfil";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login?logout=true";
    }
>>>>>>> feature/HU-SCRUM-11
}