package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "maquinaria")
public class Maquinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maquinaria")
    private Long idMaquinaria;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String marca;
    private String modelo;
    private Integer anio;

    @Column(name = "numero_serie")
    private String numeroSerie;

    private String estado;

   
    @NotNull(message = "Debe asignar una empresa")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    public Maquinaria() {}

    // Getters y Setters...

    public Empresa getEmpresa() { 
        return empresa; 
    }

<<<<<<< Updated upstream
    public void setEmpresa(Empresa empresa) { 
        this.empresa = empresa; 
=======
    public Long getIdMaquinaria() { return idMaquinaria; }
    public void setIdMaquinaria(Long idMaquinaria) { this.idMaquinaria = idMaquinaria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public Long getId() {
        return 0L;
    }

    public void setId(Long maquinaId) {
    }

    public String getUbicacion() {
        return "";
>>>>>>> Stashed changes
    }
}