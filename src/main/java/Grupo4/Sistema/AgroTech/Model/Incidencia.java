package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_falla", nullable = false)
    private String tipoFalla;

    private String severidad;
    private String ubicacion;
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha")
    private LocalDateTime fechaHora;

    // Forzamos la columna en la BD y aseguramos que no vaya nula nunca
    @Column(name = "maquina_id", nullable = false)
    private Long maquinaId = 1L;

    // Callback de JPA que se ejecuta justo antes de INSERTAR en la BD
    @PrePersist
    public void prePersist() {
        if (this.maquinaId == null) {
            this.maquinaId = 1L;
        }
        if (this.fechaHora == null) {
            this.fechaHora = LocalDateTime.now();
        }
    }

    public Incidencia() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoFalla() { return tipoFalla; }
    public void setTipoFalla(String tipoFalla) { this.tipoFalla = tipoFalla; }

    public String getSeveridad() { return severidad; }
    public void setSeveridad(String severidad) { this.severidad = severidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Long getMaquinaId() { return maquinaId; }
    public void setMaquinaId(Long maquinaId) { this.maquinaId = maquinaId; }
}