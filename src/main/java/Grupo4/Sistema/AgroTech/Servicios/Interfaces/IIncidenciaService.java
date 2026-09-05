package Grupo4.Sistema.AgroTech.Servicios.Interfaces;
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IIncidenciaService {


    Page<Incidencia> obtenerTodosPaginados(Pageable pegeable);

    List<Incidencia> listarTodas();

    @Transactional(readOnly = true)
    List<Incidencia> obtenerTodas();

    @Transactional(readOnly = true)
    Incidencia obtenerPorId(Long id);

    @Transactional(readOnly = true)
    List<Incidencia> obtenerPorMaquina(Long maquinaId);

    Incidencia guardar(Incidencia incidencia);

    void eliminarPorId(Integer id);

    @Transactional
    void eliminar(Long id);

    @Transactional
    void eliminarPorId(Long id);

    @Transactional
    Incidencia registrarIncidencia(Incidencia incidencia);

    @Transactional(readOnly = true)
    List<Incidencia> obtenerHistorialPorMaquina(Long maquinaId);
}