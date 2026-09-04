package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import java.util.List;

public interface IMaquinariaService {

    List<Maquinaria> listarTodas();

    void guardar(Maquinaria maquinaria);

    void eliminar(Long id);
}