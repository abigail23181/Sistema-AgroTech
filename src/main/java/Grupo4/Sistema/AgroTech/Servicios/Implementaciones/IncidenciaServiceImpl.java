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
    public Page<Incidencia> obtenerTodosPaginados(Pageable pageable) {
        return incidenciaRepository.findAll(pageable);
    }

    @Override
    public List<Incidencia> listarTodas() {
        return incidenciaRepository.findAll();
    }

    @Override
    public List<Incidencia> obtenerTodas() {
        return incidenciaRepository.findAll();
    }

    @Override
    public Incidencia guardar(Incidencia incidencia) {
        return registrarIncidencia(incidencia);
    }

    @Override
    public void eliminarPorId(Integer id) {

    }

    @Override
    public void eliminarPorId(Long id) {
        incidenciaRepository.deleteById(id);
    }

    @Override
    public Incidencia registrarIncidencia(Incidencia incidencia) {
        // Estado inicial por defecto
        if (incidencia.getEstado() == null || incidencia.getEstado().isBlank()) {
            incidencia.setEstado("PENDIENTE");
        }

        // Ubicación por defecto si la máquina no tiene registrada una específica
        if (incidencia.getUbicacion() == null || incidencia.getUbicacion().isBlank()) {
            incidencia.setUbicacion("Finca Principal / Ingenio Central");
        }

        Incidencia guardada = incidenciaRepository.save(incidencia);

        // Generación automática de alerta si es MODERADA, ALTA o CRÍTICA
        if ("MODERADA".equalsIgnoreCase(guardada.getSeveridad()) ||
                "ALTA".equalsIgnoreCase(guardada.getSeveridad()) ||
                "CRITICA".equalsIgnoreCase(guardada.getSeveridad())) {
            generarAlertaCorrectiva(guardada);
        }

        return guardada;
    }

    @Override
    public List<Incidencia> obtenerHistorialPorMaquina(Long maquinaId) {
        return incidenciaRepository.findByMaquinaIdOrderByFechaHoraDesc(maquinaId);
    }

    private void generarAlertaCorrectiva(Incidencia incidencia) {
        System.out.println("ALERTA GENERADA: Severidad " + incidencia.getSeveridad() +
                " en Máquina #" + incidencia.getMaquinaId() +
                " ubicada en " + incidencia.getUbicacion());
    }
}