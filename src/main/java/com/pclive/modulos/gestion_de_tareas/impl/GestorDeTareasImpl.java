package com.pclive.modulos.gestion_de_tareas.impl;

import com.pclive.modelos.Tarea;
import com.pclive.modulos.almacenamiento_de_datos.inter.TareaDao;
import com.pclive.modulos.gestion_de_tareas.inter.GestorDeTareas;
import com.pclive.modulos.gestion_de_tareas.inter.GestorDeTareas;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GestorDeTareasImpl implements GestorDeTareas {
    private final TareaDao tareaDao;

    public GestorDeTareasImpl(TareaDao tareaDao) {
        this.tareaDao = tareaDao;
    }

    @Override
    public CompletableFuture<List<Tarea>> findAll() {
        return CompletableFuture.supplyAsync(tareaDao::findAll); 
    }

    @Override
    public CompletableFuture<Void> create(Tarea tarea) {
        return CompletableFuture.runAsync(() -> tareaDao.create(tarea));
    }

    @Override
    public CompletableFuture<Void> update(Tarea tarea) {
        return CompletableFuture.runAsync(() -> tareaDao.update(tarea));
    }

    @Override
    public CompletableFuture<Void> delete(Tarea tarea) {
        return CompletableFuture.runAsync(() -> tareaDao.delete(tarea));
    }
}
