package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "maquinaria")
public class Maquinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaquinaria;


    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String modelo;

    private String estado;


    // ==========================
    // GETTERS Y SETTERS
    // ==========================

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


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public Long getId() {
        return idMaquinaria;
    }
}