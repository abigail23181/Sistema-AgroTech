package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import Grupo4.Sistema.AgroTech.Repositorios.EmpresaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Empresa empresa) {
        repository.save(empresa);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}