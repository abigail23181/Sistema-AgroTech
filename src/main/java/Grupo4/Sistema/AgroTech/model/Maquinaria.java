package Grupo4.Sistema.AgroTech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Maquinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaquinaria;

    private String nombre;
    private String marca;
    private String modelo;
    private Integer anio;
    private String numeroSerie;
    private String estado;
    private Long idEmpresa;

    public Maquinaria() {
    }

    public Long getIdMaquinaria() {
        return idMaquinaria;
    }

    public void setIdMaquinaria(Long idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
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

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}