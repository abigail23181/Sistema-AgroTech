package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Acceso;

import java.util.List;

public interface IAccesoService {
    List<Acceso> listarTodos();
    Acceso guardar(Acceso acceso);
    Acceso obtenerPorId(Long id);
    void eliminarPorId(Long id);
    Acceso autenticar(String email, String password);
}