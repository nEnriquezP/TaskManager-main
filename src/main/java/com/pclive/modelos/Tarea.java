package com.pclive.modelos;

import javax.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private Boolean estaCompletada;

    public Tarea() {
    }

    public Tarea(String descripcion, Boolean estaCompletada) {
        this.descripcion = descripcion;
        this.estaCompletada = estaCompletada;
    }

    public Tarea(Integer id, String descripcion, Boolean estaCompletada) {
        this.id = id;
        this.descripcion = descripcion;
        this.estaCompletada = estaCompletada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstaCompletada() {
        return estaCompletada;
    }

    public void setEstaCompletada(Boolean estaCompletada) {
        this.estaCompletada = estaCompletada;
    }

    @Override
    public String toString() {
        String format = "%s) descripcion = %s, estaCompletada = %s";
        return String.format(format,id, descripcion, estaCompletada);
    }
}
