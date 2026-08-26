package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import Grupo4.Sistema.AgroTech.Repositorios.TipoMantenimientoRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.ITipoMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoMantenimientoServiceImpl implements ITipoMantenimientoService {

    @Autowired
    private TipoMantenimientoRepository repository;

    @Override
    public List<TipoMantenimiento> listarTodos() {
        return repository.findAll();
    }

    @Override
    public List<TipoMantenimiento> listarActivos() {
        return repository.findByActivoTrue();
    }

    @Override
    public TipoMantenimiento guardar(TipoMantenimiento tipo) {
        if (repository.existsByNombreIgnoreCase(tipo.getNombre())) {
            throw new IllegalArgumentException("El nombre del tipo de mantenimiento ya existe.");
        }
        return repository.save(tipo);
   
    }

    @Override
    public TipoMantenimiento cambiarEstado(Long id, Boolean estado) {
        TipoMantenimiento tipo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de mantenimiento no encontrado"));
        tipo.setActivo(estado);
        return repository.save(tipo);
   
    }

    @Override
    public void eliminar(Long id) {


    }
}
