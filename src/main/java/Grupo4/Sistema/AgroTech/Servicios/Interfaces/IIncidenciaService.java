package Grupo4.Sistema.AgroTech.Servicios.Interfaces;
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IIncidenciaService {


    Page<Incidencia> obtenerTodosPaginados(Pageable pegeable);

    List<Incidencia> listarTodas();

    Incidencia guardar(Incidencia incidencia);

    void eliminarPorId(Integer id);
}