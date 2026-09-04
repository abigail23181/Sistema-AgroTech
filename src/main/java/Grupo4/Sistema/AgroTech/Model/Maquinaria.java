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

    public Maquinaria(String nombre, String marca, String modelo, Integer anio, String numeroSerie, String estado, Empresa empresa) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.numeroSerie = numeroSerie;
        this.estado = estado;
        this.empresa = empresa;
    }

    // --- GETTERS Y SETTERS ---

    public Long getIdMaquinaria() {
        return idMaquinaria;
    }

    public void setIdMaquinaria(Long idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
    }

    // Alias de conveniencia para compatibilidad con Incidencia (getId() / setId())
    public Long getId() {
        return idMaquinaria;
    }

    public void setId(Long id) {
        this.idMaquinaria = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}