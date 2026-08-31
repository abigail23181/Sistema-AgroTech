package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaquinariaServiceImpl implements IMaquinariaService {

    @Autowired
    private MaquinariaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Maquinaria> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Maquinaria maquinaria) {
        repository.save(maquinaria);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}