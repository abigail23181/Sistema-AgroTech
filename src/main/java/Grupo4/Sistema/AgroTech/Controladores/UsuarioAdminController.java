package Grupo4.Sistema.AgroTech.Controladores;

import Grupo4.Sistema.AgroTech.Model.UsuarioAdmin;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IUsuarioAdminService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios/admin")
public class UsuarioAdminController {

    @Autowired
    private IUsuarioAdminService usuarioAdminService;


    // ==========================================
    // LISTAR USUARIOS
    // URL: /usuarios/admin
    // ==========================================

    @GetMapping
    public String listar(
            HttpSession session,
            Model model) {

        if (!esAdministrador(session)) {
            return verificarAcceso(session);
        }

        model.addAttribute(
                "usuarios",
                usuarioAdminService.listarTodos()
        );

        return "usuarios_admin";
    }


    // ==========================================
    // CREAR USUARIO
    // URL: /usuarios/admin/guardar
    // ==========================================

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute UsuarioAdmin usuario,
            HttpSession session,
            RedirectAttributes redirectAttrs) {

        if (!esAdministrador(session)) {
            return verificarAcceso(session);
        }

        try {

            // Validar usuario
            if (usuario.getUsuario() == null
                    || usuario.getUsuario().trim().isEmpty()) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El usuario es obligatorio"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "warning"
                );

                return "redirect:/usuarios/admin";
            }


            // Validar correo
            if (usuario.getEmail() == null
                    || usuario.getEmail().trim().isEmpty()) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El correo es obligatorio"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "warning"
                );

                return "redirect:/usuarios/admin";
            }


            // Limpiar espacios
            usuario.setUsuario(
                    usuario.getUsuario().trim()
            );

            usuario.setEmail(
                    usuario.getEmail().trim()
            );


            // Verificar usuario duplicado
            if (usuarioAdminService.usuarioExiste(
                    usuario.getUsuario())) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El usuario ya está registrado"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "warning"
                );

                return "redirect:/usuarios/admin";
            }


            // Verificar correo duplicado
            if (usuarioAdminService.emailExiste(
                    usuario.getEmail())) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El correo electrónico ya está registrado"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "warning"
                );

                return "redirect:/usuarios/admin";
            }


            // Nuevo usuario = activo
            usuario.setActivo(true);

            usuarioAdminService.guardar(usuario);


            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "Usuario registrado exitosamente"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "success"
            );

        } catch (Exception e) {

            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "No se pudo registrar el usuario"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );
        }

        return "redirect:/usuarios/admin";
    }


    // ==========================================
    // EDITAR USUARIO
    // URL: /usuarios/admin/editar
    // ==========================================

    @PostMapping("/editar")
    public String editar(
            @ModelAttribute UsuarioAdmin usuario,
            HttpSession session,
            RedirectAttributes redirectAttrs) {

        if (!esAdministrador(session)) {
            return verificarAcceso(session);
        }

        try {

            UsuarioAdmin actual =
                    usuarioAdminService.buscarPorId(
                            usuario.getId()
                    );


            // Verificar que exista
            if (actual == null) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El usuario no existe"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "danger"
                );

                return "redirect:/usuarios/admin";
            }


            // Limpiar datos
            if (usuario.getUsuario() != null) {
                usuario.setUsuario(
                        usuario.getUsuario().trim()
                );
            }

            if (usuario.getEmail() != null) {
                usuario.setEmail(
                        usuario.getEmail().trim()
                );
            }


            // Verificar usuario duplicado
            if (usuarioAdminService.usuarioExisteEditando(
                    usuario.getUsuario(),
                    usuario.getId())) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El usuario ya está registrado"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "warning"
                );

                return "redirect:/usuarios/admin";
            }


            // Verificar correo duplicado
            if (usuarioAdminService.emailExisteEditando(
                    usuario.getEmail(),
                    usuario.getId())) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El correo electrónico ya está registrado"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "warning"
                );

                return "redirect:/usuarios/admin";
            }


            // Si no escribe contraseña,
            // conservar la contraseña anterior
            if (usuario.getClave() == null
                    || usuario.getClave().trim().isEmpty()) {

                usuario.setClave(
                        actual.getClave()
                );
            }


            // Conservar estado actual
            usuario.setActivo(
                    actual.isActivo()
            );


            usuarioAdminService.guardar(usuario);


            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "Usuario actualizado correctamente"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "success"
            );

        } catch (Exception e) {

            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "No se pudo actualizar el usuario"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );
        }

        return "redirect:/usuarios/admin";
    }


    // ==========================================
    // ACTIVAR / DESACTIVAR USUARIO
    // URL: /usuarios/admin/estado
    // ==========================================

    @PostMapping("/estado")
    public String cambiarEstado(
            @RequestParam("id") Long id,
            HttpSession session,
            RedirectAttributes redirectAttrs) {

        if (!esAdministrador(session)) {
            return verificarAcceso(session);
        }

        try {

            UsuarioAdmin usuario =
                    usuarioAdminService.buscarPorId(id);


            // Verificar que exista
            if (usuario == null) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El usuario no existe"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "danger"
                );

                return "redirect:/usuarios/admin";
            }


            boolean estabaActivo =
                    usuario.isActivo();


            usuarioAdminService.cambiarEstado(id);


            String mensaje;

            if (estabaActivo) {

                mensaje =
                        "Usuario desactivado correctamente";

            } else {

                mensaje =
                        "Usuario activado correctamente";
            }


            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    mensaje
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "success"
            );

        } catch (Exception e) {

            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "No se pudo cambiar el estado del usuario"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );
        }

        return "redirect:/usuarios/admin";
    }


    // ==========================================
    // ELIMINAR USUARIO
    // URL: /usuarios/admin/eliminar
    // ==========================================

    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam("id") Long id,
            HttpSession session,
            RedirectAttributes redirectAttrs) {

        if (!esAdministrador(session)) {
            return verificarAcceso(session);
        }

        try {

            UsuarioAdmin usuario =
                    usuarioAdminService.buscarPorId(id);


            // Verificar que exista
            if (usuario == null) {

                redirectAttrs.addFlashAttribute(
                        "mensaje",
                        "El usuario no existe"
                );

                redirectAttrs.addFlashAttribute(
                        "tipoMensaje",
                        "danger"
                );

                return "redirect:/usuarios/admin";
            }


            usuarioAdminService.eliminar(id);


            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "Usuario eliminado correctamente"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "success"
            );

        } catch (Exception e) {

            redirectAttrs.addFlashAttribute(
                    "mensaje",
                    "No se pudo eliminar el usuario"
            );

            redirectAttrs.addFlashAttribute(
                    "tipoMensaje",
                    "danger"
            );
        }

        return "redirect:/usuarios/admin";
    }


    // ==========================================
    // COMPROBAR SI ES ADMINISTRADOR
    // ==========================================

    private boolean esAdministrador(
            HttpSession session) {

        Object usuario =
                session.getAttribute(
                        "usuarioLogueado"
                );

        Object rol =
                session.getAttribute(
                        "rol"
                );

        return usuario != null
                && rol != null
                && "ADMINISTRADOR".equals(rol);
    }


    // ==========================================
    // CONTROL DE ACCESO
    // ==========================================

    private String verificarAcceso(
            HttpSession session) {

        // Si no inició sesión
        if (session.getAttribute(
                "usuarioLogueado") == null) {

            return "redirect:/login";
        }

        // Si inició sesión pero no es administrador
        return "redirect:/403";
    }
}