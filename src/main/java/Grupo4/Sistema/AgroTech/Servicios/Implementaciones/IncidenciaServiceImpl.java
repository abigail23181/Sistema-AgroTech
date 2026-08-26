package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Repositorios.IncidenciaRepository;
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
        return incidenciaRepository.findAll();
    }

    @Override
    public Incidencia guardar(Incidencia incidencia) {
        if ("ALTA".equalsIgnoreCase(incidencia.getSeveridad())) {
            // Lógica para enviar alerta si la severidad es alta
            System.out.println("ALERTA: Incidencia de severidad alta registrada en " + incidencia.getUbicacion());
        }
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public void eliminarPorId(Integer id) {

    }

    @Override
    public void eliminar(Long id) {

    }
}