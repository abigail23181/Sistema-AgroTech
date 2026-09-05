package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Repositorios.IncidenciaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Override
    public Page<Incidencia> obtenerTodosPaginados(Pageable pegeable) {
        return null;
    }

    @Override
    public List<Incidencia> listarTodas() {
        return incidenciaRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Incidencia> obtenerTodas() {
        return List.of();
    }

    @Override
    public Incidencia guardar(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public void eliminarPorId(Integer id) {

    }

    @Override
    public Incidencia obtenerPorId(Long id) {
        return incidenciaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Incidencia> obtenerPorMaquina(Long maquinaId) {
        return List.of();
    }

    @Override
    public void eliminar(Long id) {
        incidenciaRepository.deleteById(id);
    }

    @Override
    public void eliminarPorId(Long id) {

    }

    @Override
    public Incidencia registrarIncidencia(Incidencia incidencia) {
        return null;
    }

    @Override
    public List<Incidencia> obtenerHistorialPorMaquina(Long maquinaId) {
        return List.of();
    }
}