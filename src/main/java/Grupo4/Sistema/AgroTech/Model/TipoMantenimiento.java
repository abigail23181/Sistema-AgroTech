package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipomantenimiento")
public class TipoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CA03: Campo obligatorio | CA04: Nombre único
    @NotBlank(message = "El nombre del tipo de mantenimiento es obligatorio.")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    // CA02: Clasificación obligatoria (preventivo/correctivo)
    @NotBlank(message = "Debe seleccionar una categoría (preventivo o correctivo).")
    @Column(nullable = false, length = 20)
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // CA07: Estado activo/inactivo
    @Column(nullable = false)
    private Boolean activo = true;

    public TipoMantenimiento() {}

    public TipoMantenimiento(Long id, String nombre, String categoria, String descripcion, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}