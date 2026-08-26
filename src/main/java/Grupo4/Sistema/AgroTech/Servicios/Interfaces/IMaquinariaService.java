package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import java.util.List;
import java.util.Optional;

public interface IMaquinariaService {
    List<Maquinaria> obtenerTodas();
    Optional<Maquinaria> obtenerPorId(Long id);
    Maquinaria guardar(Maquinaria maquinaria);
    void eliminar(Long id);

    Object listarTodas();
}