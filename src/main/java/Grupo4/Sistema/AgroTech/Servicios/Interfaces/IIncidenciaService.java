package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import java.util.List;

public interface IIncidenciaService {
    List<Incidencia> listarTodas();
    void guardar(Incidencia incidencia);
    void eliminar(Long id);
}