package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
<<<<<<< HEAD
import java.time.LocalDateTime;
=======

import java.time.LocalDate;
>>>>>>> feature/HU-SCRUM-11

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
<<<<<<< HEAD
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
=======

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 30)
    private String estado;

    // RELACIÓN CON MAQUINARIA
    @ManyToOne
    @JoinColumn(name = "maquina_id", nullable = false)
    private Maquinaria maquinaria;

    public Incidencia() {
    }

    public Incidencia(Long id, String severidad, LocalDate fecha,
                      String ubicacion, String descripcion,
                      String estado, Maquinaria maquinaria) {
        this.id = id;
        this.severidad = severidad;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.maquinaria = maquinaria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Maquinaria getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(Maquinaria maquinaria) {
        this.maquinaria = maquinaria;
    }
>>>>>>> feature/HU-SCRUM-11
}