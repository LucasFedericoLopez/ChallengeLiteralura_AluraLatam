package com.example.aluracursos.challengeliteralura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> tClass);
}
