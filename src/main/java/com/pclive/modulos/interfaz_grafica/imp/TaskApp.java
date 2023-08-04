package com.pclive.modulos.interfaz_grafica.imp;

import com.pclive.modelos.Tarea;
import com.pclive.modulos.gestion_de_tareas.inter.GestorDeTareas;
import com.pclive.modulos.interfaz_grafica.inter.InterfazGrafica;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class TaskApp implements InterfazGrafica {

    private final Scanner scanner = new Scanner(System.in);
    private final GestorDeTareas gestor;
    private boolean ejecutando = true;

    public TaskApp(GestorDeTareas gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        try {
            while (ejecutando){
                mostrarMenu();
                String opcion = scanner.nextLine();
                ejecutarAccion(opcion);
            }
        }catch (Exception e){
            String exception = Arrays.stream(e.getStackTrace()).findFirst().get().toString();
            String message = e.getMessage();
            System.out.println( exception+" "+message);
            run();
        }
    }

    private void mostrarMenu() {
        System.out.println("|| TASKAPP ||");
        System.out.println("1. Ver tareas");
        System.out.println("2. Crear tarea");
        System.out.println("3. Actualizar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("0. Salir");
    }

    private void ejecutarAccion(String opcion) throws ExecutionException, InterruptedException {
        switch (opcion){
            case "1":
                mostrarTareas(gestor.findAll().get());
                break;
            case "2":
                gestor.create(crearTarea())
                        .thenAccept(result -> System.out.println("Creada"));
                break;
            case "3":
                gestor.update(actualizarTarea())
                        .thenAccept(result -> System.out.println("Actualizada"));
                break;
            case "4":
                gestor.delete(eliminarTarea())
                        .thenAccept(result -> System.out.println("Eliminada"));
                break;
            case "0":
                ejecutando = false;
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void mostrarTareas(List<Tarea> tareas){
        System.out.println();
        if (!tareas.isEmpty()){
            tareas.forEach(System.out::println);
        }else {
            System.out.println("No hay tareas");
        }
        System.out.println();
    }

    private Tarea crearTarea(){
        Tarea tarea = new Tarea();
        System.out.println("Ingresa la descripcion");
        tarea.setDescripcion(scanner.nextLine());
        System.out.println("Esta completada? Y(Si) N(No)");
        tarea.setEstaCompletada(scanner.nextLine().equalsIgnoreCase("Y"));
        return tarea;
    }

    private Tarea actualizarTarea(){
        Tarea tarea = new Tarea();
        System.out.println("Ingresa el Id");
        tarea.setId(Integer.valueOf(scanner.nextLine()));
        System.out.println("Ingresa la descripcion");
        tarea.setDescripcion(scanner.nextLine());
        System.out.println("Esta completada? Y(Si) N(No)");
        tarea.setEstaCompletada(scanner.nextLine().equalsIgnoreCase("Y"));
        return tarea;
    }

    private Tarea eliminarTarea() {
        Tarea tarea = new Tarea();
        System.out.println("Ingresa el Id");
        tarea.setId(Integer.valueOf(scanner.nextLine()));
        return tarea;
    }
}
