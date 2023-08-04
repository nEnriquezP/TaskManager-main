package com.pclive.modulos.almacenamiento_de_datos.impl;

import com.pclive.modelos.Tarea;
import com.pclive.modulos.almacenamiento_de_datos.inter.TareaDao;

import javax.persistence.EntityManager;
import java.util.List;

public class TareaDaoImpl implements TareaDao {

    private final EntityManager entityManager;

    public TareaDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Tarea> findAll() {
        String jpql = "SELECT t FROM Tarea t";
        List<Tarea> tareas;
        entityManager.getTransaction().begin();
        entityManager.flush(); // Sincroniza con la base de datos
        entityManager.clear(); // Limpia cache.
        tareas = entityManager.createQuery(jpql, Tarea.class).getResultList();
        entityManager.getTransaction().commit();
        return tareas;
    }

    @Override
    public void create(Tarea tarea) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(tarea);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tarea tarea) {
        try{
            String jpql = "UPDATE Tarea t SET t.descripcion = :descripcion, t.estaCompletada = :estaCompletada WHERE t.id = :id";
            entityManager.getTransaction().begin();
            entityManager.createQuery(jpql)
                            .setParameter("descripcion", tarea.getDescripcion())
                            .setParameter("estaCompletada", tarea.getEstaCompletada())
                            .setParameter("id", tarea.getId())
                            .executeUpdate();
            entityManager.flush();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Tarea tarea) {
        try{
            String jpql = "DELETE FROM Tarea t WHERE t.id = :id";
            entityManager.getTransaction().begin();
            entityManager.createQuery(jpql)
                    .setParameter("id", tarea.getId())
                    .executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
