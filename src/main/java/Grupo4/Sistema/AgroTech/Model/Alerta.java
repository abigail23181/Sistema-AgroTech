package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maquinaria_id")
    private Maquinaria maquinaria;

    private String ubicacion;
    private String tipo;
    private LocalDate fechaLimite;
    private String estado;

    public Alerta() {}

    public Alerta(Maquinaria maquinaria, String ubicacion, String tipo, LocalDate fechaLimite, String estado) {
        this.maquinaria = maquinaria;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Maquinaria getMaquinaria() { return maquinaria; }
    public void setMaquinaria(Maquinaria maquinaria) { this.maquinaria = maquinaria; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}