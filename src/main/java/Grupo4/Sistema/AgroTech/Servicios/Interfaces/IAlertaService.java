package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import java.util.List;

public interface IAlertaService {
    List<Alerta> listarTodas();
    void guardar(Alerta alerta);
    void eliminar(Long id);
}