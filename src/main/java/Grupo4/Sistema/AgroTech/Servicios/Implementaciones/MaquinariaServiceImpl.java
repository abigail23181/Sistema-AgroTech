package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Maquinaria;
import Grupo4.Sistema.AgroTech.Repositorios.MaquinariaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IMaquinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinariaServiceImpl implements IMaquinariaService {

    @Autowired
    private MaquinariaRepository maquinariaRepository;

    @Override
    public List<Maquinaria> listarTodas() {
        return maquinariaRepository.findAll();
    }

    @Override
    public void guardar(Maquinaria maquinaria) {
        maquinariaRepository.save(maquinaria);
    }

    @Override
    public void eliminar(Long id) {
        maquinariaRepository.deleteById(id);
    }
}