package Grupo4.Sistema.AgroTech.Servicios.Implementaciones;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import Grupo4.Sistema.AgroTech.Repositorios.AlertaRepository;
import Grupo4.Sistema.AgroTech.Servicios.Interfaces.IAlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaServiceImpl implements IAlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Override
    public List<Alerta> obtenerAlertas(String tipo, String ubicacion, Long maquinariaId) {
        return alertaRepository.buscarConFiltros(
                (tipo != null && !tipo.isBlank()) ? tipo : null,
                (ubicacion != null && !ubicacion.isBlank()) ? ubicacion : null,
                maquinariaId
        );
    }
}