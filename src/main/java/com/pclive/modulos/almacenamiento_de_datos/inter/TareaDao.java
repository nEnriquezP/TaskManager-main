package com.pclive.modulos.almacenamiento_de_datos.inter;

import com.pclive.modelos.Tarea;

import java.util.List;

public interface TareaDao {
    List<Tarea> findAll();
    void create(Tarea tarea);
    void update(Tarea tarea);
    void delete(Tarea tarea);
}
