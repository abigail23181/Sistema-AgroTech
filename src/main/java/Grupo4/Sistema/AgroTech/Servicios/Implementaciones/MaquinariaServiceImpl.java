package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinariaServiceImpl implements IMaquinariaService {

    @Autowired
    private MaquinariaRepository repository;


    // ==========================
    // LISTAR
    // ==========================
    @Override
    @Transactional(readOnly = true)
    public List<Maquinaria> listarTodas() {
        return repository.findAll();
    }


    // ==========================
    // GUARDAR / EDITAR
    // ==========================
    @Override
    @Transactional
    public void guardar(Maquinaria maquinaria) {
        repository.save(maquinaria);
    }


    // ==========================
    // ELIMINAR
    // ==========================
    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }


    // ==========================
    // BUSCAR POR ID
    // ==========================
    @Override
    @Transactional(readOnly = true)
    public Optional<Maquinaria> buscarPorId(Long idMaquinaria) {
        return repository.findById(idMaquinaria);
    }
}