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

    private final MaquinariaRepository maquinariaRepository;

    @Autowired
    public MaquinariaServiceImpl(MaquinariaRepository maquinariaRepository) {
        this.maquinariaRepository = maquinariaRepository;
    }

    @Override
    public List<Maquinaria> obtenerTodas() {
        return maquinariaRepository.findAll();
    }

    @Override
    public Optional<Maquinaria> obtenerPorId(Long id) {
        return maquinariaRepository.findById(id);
    }

    @Override
    public Maquinaria guardar(Maquinaria maquinaria) {
        return maquinariaRepository.save(maquinaria);
    }

    @Override
    public void eliminar(Long id) {
        maquinariaRepository.deleteById(id);
    }
}