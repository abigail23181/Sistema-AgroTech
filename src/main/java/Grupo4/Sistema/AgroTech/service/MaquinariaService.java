package Grupo4.Sistema.AgroTech.service;

import Grupo4.Sistema.AgroTech.model.Maquinaria;
import Grupo4.Sistema.AgroTech.repository.MaquinariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinariaService {

    private final MaquinariaRepository maquinariaRepository;

    public MaquinariaService(MaquinariaRepository maquinariaRepository) {
        this.maquinariaRepository = maquinariaRepository;
    }

    public List<Maquinaria> obtenerTodos() {
        return maquinariaRepository.findAll();
    }
}
