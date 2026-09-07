package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import java.util.List;

public interface IncidenciaService {
    List<Incidencia> listarTodas();
    Incidencia guardar(Incidencia incidencia);
    Incidencia obtenerPorId(Long id);
    void eliminar(Long id);
}