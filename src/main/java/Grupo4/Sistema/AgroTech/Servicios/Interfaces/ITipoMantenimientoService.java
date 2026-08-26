package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import java.util.List;

public interface ITipoMantenimientoService {

    List<TipoMantenimiento> listarTodos();
    List<TipoMantenimiento> listarActivos();
    TipoMantenimiento guardar(TipoMantenimiento tipoMantenimiento);
    TipoMantenimiento cambiarEstado(Long id, Boolean estado);

    void eliminar(Long id);


}