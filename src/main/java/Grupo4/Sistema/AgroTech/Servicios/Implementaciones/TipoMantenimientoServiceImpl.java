package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.TipoMantenimiento;
import Grupo4.Sistema.AgroTech.Repositorios.TipoMantenimientoRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.ITipoMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoMantenimientoServiceImpl implements ITipoMantenimientoService {

    @Autowired
    private TipoMantenimientoRepository tipoMantenimientoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoMantenimiento> listarTodos() {
        return tipoMantenimientoRepository.findAll();
    }

    // CA06: Filtra solo los tipos de mantenimiento activos para asociarlos en incidencias/alertas
    @Override
    @Transactional(readOnly = true)
    public List<TipoMantenimiento> listarActivos() {
        return tipoMantenimientoRepository.findByActivoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoMantenimiento obtenerPorId(Long id) {
        return tipoMantenimientoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TipoMantenimiento guardar(TipoMantenimiento tipoMantenimiento) {
        return tipoMantenimientoRepository.save(tipoMantenimiento);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        tipoMantenimientoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return false;
        }
        return tipoMantenimientoRepository.existsByNombreIgnoreCase(nombre.trim());
    }
}