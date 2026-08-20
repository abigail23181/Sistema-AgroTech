package Grupo4.Sistema.AgroTech.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El RUC es obligatorio")
    private String ruc;

    private String direccion;
    private String telefono;

    @Email(message = "Correo inválido")
    private String correo;

    private Integer estado = 1;

    
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maquinaria> maquinarias = new ArrayList<>();

    public Empresa() {}

    // Getters y Setters existentes...

    public List<Maquinaria> getMaquinarias() { 
        return maquinarias; 
    }

    public void setMaquinarias(List<Maquinaria> maquinarias) { 
        this.maquinarias = maquinarias; 
    }

    // Método de apoyo para vincular fácilmente
    public void agregarMaquinaria(Maquinaria maquinaria) {
        maquinarias.add(maquinaria);
        maquinaria.setEmpresa(this);
    }
}