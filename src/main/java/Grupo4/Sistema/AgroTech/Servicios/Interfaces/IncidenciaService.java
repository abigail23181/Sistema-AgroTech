package Grupo4.Sistema.AgroTech.Servicios.Interfaces;
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface IncidenciaService {
    Incidencia registrarIncidencia(Incidencia incidencia);
    List<Incidencia> obtenerHistorialPorMaquina(Long maquinaId);

    @Nullable Object obtenerTodas();

    void eliminarPorId(Long id);
}