package com.aluracursos.forohub.infra.validaciones;

public interface ValidadorReglasDeNegocio<T> {
    public void validar(T T);
}
