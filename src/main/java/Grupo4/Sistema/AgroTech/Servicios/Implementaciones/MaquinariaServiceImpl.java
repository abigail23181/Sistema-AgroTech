package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinariaServiceImpl implements IMaquinariaService {

    @Autowired
    private MaquinariaRepository maquinariaRepository;


    // ==========================
    // LISTAR
    // ==========================
    @Override
    public List<Maquinaria> listarTodas() {
        return maquinariaRepository.findAll();
    }


    // ==========================
    // GUARDAR / EDITAR
    // ==========================
    @Override
    public void guardar(Maquinaria maquinaria) {
        maquinariaRepository.save(maquinaria);
    }


    // ==========================
    // ELIMINAR
    // ==========================
    @Override
    public void eliminar(Long id) {
        maquinariaRepository.deleteById(id);
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