package Grupo4.Sistema.AgroTech.Servicios.Interfaces;
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Model.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IIncidenciaService {


    Page<Incidencia> obtenerTodosPaginados(Pageable pegeable);

    List<Incidencia> listarTodas();

    List<Incidencia> obtenerTodas();

    Incidencia guardar(Incidencia incidencia);

    void eliminarPorId(Integer id);

    void eliminarPorId(Long id);

    Incidencia registrarIncidencia(Incidencia incidencia);

    List<Incidencia> obtenerHistorialPorMaquina(Long maquinaId);
}