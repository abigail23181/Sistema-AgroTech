package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import java.util.List;

public interface ITipoMantenimientoService {
    List<TipoMantenimiento> listarTodos();
    List<TipoMantenimiento> listarActivos(); // Para CA06 (Disponibilidad en incidencias/alertas)
    TipoMantenimiento obtenerPorId(Long id);
    TipoMantenimiento guardar(TipoMantenimiento tipoMantenimiento);
    void eliminar(Long id);
    boolean existePorNombre(String nombre); // Para CA04
}