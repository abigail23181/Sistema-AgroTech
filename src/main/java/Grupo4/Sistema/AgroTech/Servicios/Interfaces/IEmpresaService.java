package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface IEmpresaService {
    List<Empresa> obtenerTodas();
    Optional<Empresa> obtenerPorId(Long id);
    Empresa guardar(Empresa empresa);
    void eliminar(Long id);

    @Nullable Object listarTodas();
    
}