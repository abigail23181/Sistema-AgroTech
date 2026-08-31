package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "La ubicación es obligatoria")
    @Column(nullable = false, length = 150)
    private String ubicacion;

    @NotBlank(message = "El tipo de alerta es obligatorio")
    @Column(nullable = false, length = 100)
    private String tipo;

    @NotNull(message = "La fecha límite es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 50)
    private String estado = "Pendiente";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_maquinaria", nullable = false)
    private Maquinaria maquinaria;

    public Alerta() {}

    public Alerta(Long id, String ubicacion, String tipo, LocalDate fechaLimite, String estado, Maquinaria maquinaria) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.maquinaria = maquinaria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Maquinaria getMaquinaria() { return maquinaria; }
    public void setMaquinaria(Maquinaria maquinaria) { this.maquinaria = maquinaria; }
}