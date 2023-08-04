package com.pclive.modulos.gestion_de_tareas.inter;

import com.pclive.modelos.Tarea;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GestorDeTareas {
    CompletableFuture<List<Tarea>> findAll(); //Objeto para manejar hilos :D [Concurrencia]
    CompletableFuture<Void> create(Tarea tarea);
    CompletableFuture<Void> update(Tarea tarea);
    CompletableFuture<Void> delete(Tarea tarea);
}
