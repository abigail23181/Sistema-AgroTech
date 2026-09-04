package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
<<<<<<< Updated upstream
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
=======
import java.time.LocalDateTime;
>>>>>>> Stashed changes

@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< Updated upstream
    @NotBlank(message = "La severidad es obligatoria")
    private String severidad;

    private LocalDate fecha;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    private String estado;

    public Incidencia() {}

    public Incidencia(String severidad, LocalDate fecha, String ubicacion, String estado) {
        this.severidad = severidad;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    // Getters y Setters
=======
    // cascade MERGE/PERSIST para evitar el error TransientPropertyValueException
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "maquina_id", nullable = true)
    private Maquinaria maquina;

    private String tipoFalla;
    private String severidad;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String ubicacion;
    private String tipoMantenimientoCorrectivo;
    private String estado = "PENDIENTE";

    public Incidencia() {}

>>>>>>> Stashed changes
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Maquinaria getMaquina() { return maquina; }
    public void setMaquina(Maquinaria maquina) { this.maquina = maquina; }

    public Long getMaquinaId() {
        return (maquina != null) ? maquina.getId() : null;
    }

    public String getTipoFalla() { return tipoFalla; }
    public void setTipoFalla(String tipoFalla) { this.tipoFalla = tipoFalla; }

    public String getSeveridad() { return severidad; }
    public void setSeveridad(String severidad) { this.severidad = severidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getTipoMantenimientoCorrectivo() { return tipoMantenimientoCorrectivo; }
    public void setTipoMantenimientoCorrectivo(String tipoMantenimientoCorrectivo) { this.tipoMantenimientoCorrectivo = tipoMantenimientoCorrectivo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}