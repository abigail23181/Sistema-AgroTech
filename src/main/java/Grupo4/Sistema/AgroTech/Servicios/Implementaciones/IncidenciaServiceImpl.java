package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Repositorios.IncidenciaRepository;
<<<<<<< Updated upstream
=======
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IncidenciaService;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream
=======

import java.time.LocalDateTime;
>>>>>>> Stashed changes
import java.util.List;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;
<<<<<<< Updated upstream

    @Override
    public Page<Incidencia> obtenerTodosPaginados(Pageable pegeable) {
        return null;
    }

    @Override
    public List<Incidencia> listarTodas() {
=======

    @Override
    public List<Incidencia> obtenerTodas() {
>>>>>>> Stashed changes
        return incidenciaRepository.findAll();
    }

    @Override
<<<<<<< Updated upstream
    public Incidencia guardar(Incidencia incidencia) {
        if ("ALTA".equalsIgnoreCase(incidencia.getSeveridad())) {
            // Lógica para enviar alerta si la severidad es alta
            System.out.println("ALERTA: Incidencia de severidad alta registrada en " + incidencia.getUbicacion());
        }
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public void eliminarPorId(Integer id) {

=======
    public void eliminarPorId(Long id) {

    }

    @Override
    public Incidencia registrarIncidencia(Incidencia incidencia) {
        // CA08: Estado inicial
        if (incidencia.getEstado() == null) {
            incidencia.setEstado("PENDIENTE");
        }

        // CA04: Ubicación por defecto si la máquina no tiene registrada una específica
        if (incidencia.getUbicacion() == null || incidencia.getUbicacion().isBlank()) {
            incidencia.setUbicacion("Finca Principal / Ingenio Central");
        }

        Incidencia guardada = incidenciaRepository.save(incidencia);

        // CA06: Generación automática de alerta si es MODERADA o CRÍTICA
        if ("MODERADA".equalsIgnoreCase(guardada.getSeveridad()) || "CRITICA".equalsIgnoreCase(guardada.getSeveridad())) {
            generarAlertaCorrectiva(guardada);
        }

        return guardada;
    }

    @Override
    public List<Incidencia> obtenerHistorialPorMaquina(Long maquinaId) {
        return incidenciaRepository.findByMaquinaIdOrderByFechaHoraDesc(maquinaId); // CA07
    }

    private void generarAlertaCorrectiva(Incidencia incidencia) {
        // Lógica automática para notificar al administrador/responsable
        System.out.println("ALERTA GENERADA: Severidad " + incidencia.getSeveridad() + " en Máquina #" + incidencia.getMaquinaId());
>>>>>>> Stashed changes
    }
}