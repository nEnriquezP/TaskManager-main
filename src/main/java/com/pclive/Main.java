package com.pclive;

import com.pclive.modulos.almacenamiento_de_datos.impl.TareaDaoImpl;
import com.pclive.modulos.almacenamiento_de_datos.inter.TareaDao;
import com.pclive.modulos.gestion_de_tareas.impl.GestorDeTareasImpl;
import com.pclive.modulos.gestion_de_tareas.inter.GestorDeTareas;
import com.pclive.modulos.interfaz_grafica.imp.TaskApp;
import com.pclive.modulos.interfaz_grafica.inter.InterfazGrafica;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("TareasDB")
                                        .createEntityManager();
        TareaDao dao = new TareaDaoImpl(entityManager);
        GestorDeTareas gestor = new GestorDeTareasImpl(dao);
        InterfazGrafica app = new TaskApp(gestor);
        app.run();
    }
}