package com.studio.oscar.agendaescolar.Datos;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

public class DeleteFiles {

    public static void todo(){
        File[] asignaturas = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles();
        File[] notas = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9").listFiles();
        File[] tareas = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles();
        File[] lunes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/0").listFiles();
        File[] martes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/1").listFiles();
        File[] miercoles = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/2").listFiles();
        File[] jueves = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/3").listFiles();
        File[] viernes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/4").listFiles();

        for(File archivosA : asignaturas)
            archivosA.delete();

        for (File archivosN : notas)
            archivosN.delete();

        for (File archivost : tareas)
            archivost.delete();

        for (File archivosL : lunes)
            archivosL.delete();

        for (File archivosM : martes)
            archivosM.delete();

        for (File archivosMi : miercoles)
            archivosMi.delete();

        for (File archivosJ : jueves)
            archivosJ.delete();

        for (File archivosV : viernes)
            archivosV.delete();
    }

    public static void asignaturas(){
        File[] lunes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/0").listFiles();
        File[] martes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/1").listFiles();
        File[] miercoles = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/2").listFiles();
        File[] jueves = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/3").listFiles();
        File[] viernes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/4").listFiles();

        for (File archivosL : lunes)
            archivosL.delete();

        for (File archivosM : martes)
            archivosM.delete();

        for (File archivosMi : miercoles)
            archivosMi.delete();

        for (File archivosJ : jueves)
            archivosJ.delete();

        for (File archivosV : viernes)
            archivosV.delete();
    }

    public static void tareas(){
        File[] tareas = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles();

        for (File archivost : tareas)
            archivost.delete();
    }

    public static void notas(){
        File[] notas = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9").listFiles();
        for (File archivosN : notas)
            archivosN.delete();
    }

}
