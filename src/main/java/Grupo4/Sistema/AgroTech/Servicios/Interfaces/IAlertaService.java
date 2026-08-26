package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import java.util.List;
import java.util.Optional;

public interface IAlertaService {
    List<Alerta> obtenerAlertas(String tipo, String ubicacion, Long maquinariaId);

    List<Alerta> obtenerTodas();
    List<Alerta> obtenerAlertasFiltradas(String tipo, String ubicacion, Long maquinariaId);
    Optional<Alerta> obtenerPorId(Long id);
    Alerta guardar(Alerta alerta);
    void eliminar(Long id);
}