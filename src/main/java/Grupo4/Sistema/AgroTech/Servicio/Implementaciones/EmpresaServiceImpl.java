package Grupo4.Sistema.AgroTech.Servicio.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import Grupo4.Sistema.AgroTech.Repositorios.EmpresaRepository;
import Grupo4.Sistema.AgroTech.Servicio.Interfaces.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empresa obtenerPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public Empresa guardar(Empresa empresa) {
        if (existeCorreo(empresa.getCorreo(), null)) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }
        if (existeRuc(empresa.getRuc(), null)) {
            throw new IllegalArgumentException("El RUC/NIT ya está registrado.");
        }
        if (empresa.getEstado() == null) {
            empresa.setEstado(true);
        }
        return empresaRepository.save(empresa);
    }

    @Override
    @Transactional
    public Empresa actualizar(Long id, Empresa detalles) {
        Empresa existente = obtenerPorId(id);

        if (existeCorreo(detalles.getCorreo(), id)) {
            throw new IllegalArgumentException("El correo ya está registrado por otra empresa.");
        }
        if (existeRuc(detalles.getRuc(), id)) {
            throw new IllegalArgumentException("El RUC/NIT ya está registrado por otra empresa.");
        }

        existente.setNombre(detalles.getNombre());
        existente.setCorreo(detalles.getCorreo());
        existente.setRuc(detalles.getRuc());
        existente.setTelefono(detalles.getTelefono());
        existente.setDireccion(detalles.getDireccion());

        return empresaRepository.save(existente);
    }

    @Override
    @Transactional
    public void cambiarEstado(Long id, Boolean estado) {
        Empresa empresa = obtenerPorId(id);
        empresa.setEstado(estado);
        empresaRepository.save(empresa);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCorreo(String correo, Long idExcluir) {
        if (idExcluir == null) {
            return empresaRepository.existsByCorreo(correo);
        }
        return empresaRepository.existsByCorreoAndIdNot(correo, idExcluir);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeRuc(String ruc, Long idExcluir) {
        if (idExcluir == null) {
            return empresaRepository.existsByRuc(ruc);
        }
        return empresaRepository.existsByRucAndIdNot(ruc, idExcluir);
    }
}