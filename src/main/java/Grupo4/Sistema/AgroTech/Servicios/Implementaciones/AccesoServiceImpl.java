package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Acceso;
import Grupo4.Sistema.AgroTech.Repositorios.AccesoRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccesoServiceImpl implements IAccesoService {

    @Autowired
    private AccesoRepository accesoRepository;

    public AccesoServiceImpl() {
    }

    @Override
    public List<Acceso> listarTodos() {
        return accesoRepository.findAll();
    }

    @Override
    public Acceso guardar(Acceso acceso) {
        // CORREGIDO: Usamos la variable de instancia 'accesoRepository'
        return accesoRepository.save(acceso);
    }

    @Override
    public Acceso obtenerPorId(Long id) {
        return accesoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPorId(Long id) {
        accesoRepository.deleteById(id);
    }

    @Override
    public Acceso autenticar(String email, String password) {
        Acceso acceso = accesoRepository.findByEmail(email);
        if (acceso != null && acceso.getPassword().equals(password)) {
            return acceso;
        }
        return null;
    }
}