package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import java.util.List;

public interface ITipoMantenimientoService {
    List<TipoMantenimiento> listarTodos();
    void guardar(TipoMantenimiento tipoMantenimiento);
    void cambiarEstado(Long id, Boolean activo);
    void eliminar(Long id);

    TipoMantenimiento obtenerPorId(Long id);
}