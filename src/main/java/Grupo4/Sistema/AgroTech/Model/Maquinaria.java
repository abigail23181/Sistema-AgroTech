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

    public void setEmpresa(Empresa empresa) { 
        this.empresa = empresa; 
    }
}