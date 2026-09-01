package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Repositorios.IncidenciaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService {

    @Autowired
    private IncidenciaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Incidencia> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Incidencia incidencia) {
        repository.save(incidencia);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}