package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import Grupo4.Sistema.AgroTech.Repositorios.TipoMantenimientoRepository;
import Grupo4.Sistema.AgroTech.Repositorios.TipoMantenimientoRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.ITipoMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoMantenimientoServiceImpl implements ITipoMantenimientoService {

    @Autowired
    private TipoMantenimientoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoMantenimiento> listarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void guardar(TipoMantenimiento tipoMantenimiento) {
        repository.save(tipoMantenimiento);
    }

    @Override
    @Transactional
    public void cambiarEstado(Long id, Boolean activo) {
        repository.findById(id).ifPresent(tm -> {
            tm.setActivo(activo);
            repository.save(tm);
        });
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}