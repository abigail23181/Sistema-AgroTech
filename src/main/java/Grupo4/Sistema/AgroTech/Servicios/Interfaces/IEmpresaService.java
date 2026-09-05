package Grupo4.Sistema.AgroTech.Servicios.Interfaces;

import Grupo4.Sistema.AgroTech.Model.Empresa;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;

import java.util.List;

public interface IEmpresaService {
    @Nullable
    Object listarTodas();
    void guardar(Empresa empresa);
    void eliminar(Long id);

}