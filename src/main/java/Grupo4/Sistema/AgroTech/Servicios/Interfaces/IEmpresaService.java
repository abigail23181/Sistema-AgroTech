package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface IEmpresaService {
    List<Empresa> listarTodas();
    void guardar(Empresa empresa);
    void eliminar(Long id);

    @Nullable Object listarTodas();
}