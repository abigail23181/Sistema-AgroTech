package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- AGREGA ESTA LÍNEA ---
    private String tipo;

    private String estado;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Column(length = 1000)
    private String observaciones;

    @Column(name = "historial_mantenimientos", length = 1000)
    private String historialMantenimientos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maquinaria_id")
    private Maquinaria maquinaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_mantenimiento_id")
    private TipoMantenimiento tipoMantenimiento;

    public Alerta() {}

    // --- AGREGA ESTOS MÉTODOS GETTER Y SETTER ---
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getHistorialMantenimientos() { return historialMantenimientos; }
    public void setHistorialMantenimientos(String historialMantenimientos) { this.historialMantenimientos = historialMantenimientos; }

    public Maquinaria getMaquinaria() { return maquinaria; }
    public void setMaquinaria(Maquinaria maquinaria) { this.maquinaria = maquinaria; }

    public TipoMantenimiento getTipoMantenimiento() { return tipoMantenimiento; }
    public void setTipoMantenimiento(TipoMantenimiento tipoMantenimiento) { this.tipoMantenimiento = tipoMantenimiento; }
}