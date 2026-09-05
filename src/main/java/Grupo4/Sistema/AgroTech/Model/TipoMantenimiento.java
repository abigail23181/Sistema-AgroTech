package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_mantenimiento")
public class TipoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }
}