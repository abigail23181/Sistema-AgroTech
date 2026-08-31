package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "maquinarias")
public class Maquinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maquinaria")
    private Long idMaquinaria;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 150)
    private String nombre;

    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false, length = 100)
    private String modelo;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 50)
    private String estado = "Operativo";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", nullable = false)
    @NotNull(message = "La empresa es obligatoria")
    private Empresa empresa;

    public Maquinaria() {}

    public Maquinaria(Long idMaquinaria, String nombre, String modelo, String estado, Empresa empresa) {
        this.idMaquinaria = idMaquinaria;
        this.nombre = nombre;
        this.modelo = modelo;
        this.estado = estado;
        this.empresa = empresa;
    }

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
}