package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Alerta;
import java.util.List;

public interface IAlertaService {
    List<Alerta> obtenerAlertas(String tipo, String ubicacion, Long maquinariaId);
}