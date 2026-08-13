package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import java.util.List;

public interface IEmpresaService {
    List<Empresa> listarTodas();
    Empresa obtenerPorId(Long id);
    Empresa guardar(Empresa empresa);
    Empresa actualizar(Long id, Empresa empresa);
    void cambiarEstado(Long id, Boolean estado);
    boolean existeCorreo(String correo, Long idExcluir);
    boolean existeRuc(String ruc, Long idExcluir);

}