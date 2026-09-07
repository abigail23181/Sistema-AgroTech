package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
<<<<<<< HEAD
@Table(name = "tipo_mantenimiento")
=======
@Table(name = "tipomantenimiento")
>>>>>>> feature/HU-SCRUM-11
public class TipoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false)
=======
    @NotBlank(message = "El nombre del tipo de mantenimiento es obligatorio.")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
    @Column(nullable = false, unique = true, length = 100)
>>>>>>> feature/HU-SCRUM-11
    private String nombre;

    @NotBlank(message = "Debe seleccionar una categoría (preventivo o correctivo).")
    @Column(nullable = false, length = 20)
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private boolean activo = true;

    // Campo de la base de datos que está fallando
    @Column(name = "clasificacion", nullable = false)
    private String clasificacion = "GENERAL";

    // Asigna un valor por defecto antes de guardar en MySQL
    @PrePersist
    public void prePersist() {
        if (this.clasificacion == null || this.clasificacion.trim().isEmpty()) {
            this.clasificacion = "GENERAL";
        }
    }

    public TipoMantenimiento() {}

<<<<<<< HEAD
=======
    public TipoMantenimiento(Long id, String nombre, String categoria, String descripcion, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.activo = activo;
    }

>>>>>>> feature/HU-SCRUM-11
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }
}