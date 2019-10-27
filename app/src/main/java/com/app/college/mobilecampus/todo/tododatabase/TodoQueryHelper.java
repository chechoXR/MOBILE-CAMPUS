package com.app.college.mobilecampus.todo.tododatabase;

public class TodoQueryHelper {

    public static final String[] PROJECTION_TAREA = {TodoTareaEntry.ID,TodoTareaEntry.NAME,TodoTareaEntry.INIT_DATE,TodoTareaEntry.END_DATE,TodoTareaEntry.ID_MATERIA,TodoTareaEntry.COMPLETA};
    public static final String[] PROJECTION_MATERIA = {TodoMateriaEntry.ID,TodoMateriaEntry.MATERIA};

    //Crear aqui los selection necesarios para lo que se va a necesitar

}
