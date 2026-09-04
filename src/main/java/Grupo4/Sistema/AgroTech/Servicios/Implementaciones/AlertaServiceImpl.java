package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import Grupo4.Sistema.AgroTech.Model.Incidencia;
import Grupo4.Sistema.AgroTech.Repositorios.AlertaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertaServiceImpl implements IAlertaService {

    @Autowired
    private AlertaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Alerta> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Alerta alerta) {
        repository.save(alerta);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void generarAlertaCorrectiva(Incidencia incidenciaGuardada) {

    }
}