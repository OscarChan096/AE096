package com.studio.oscar.agendaescolar.CreatedFiles;

import com.studio.oscar.agendaescolar.Datos.Read;
import com.studio.oscar.agendaescolar.Datos.ReadDate;
import com.studio.oscar.agendaescolar.Datos.Write;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class mkdirsDirectorios {

    public static void createdDirectory12x() {
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/12x");
        if (!path.isDirectory()) {
            path.mkdirs();
            Write.WriteInfoApp(ReadDate.getDia(), false, false);
        }
    }

    public static void createdDirectory(String diaActual, boolean check) {
        File pathSettings = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/00"); // settings
        File pathAsign = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7"); // asignaturas
        File pathInfoUser = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf"); // informacion de usuario
        File pathNotas = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9"); // notas
        File pathTareas = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8"); // tareas
        File pathLunes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/0"); //lunes
        File pathMartes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/1"); // martes
        File pathMiercoles = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/2"); // miercoles
        File pathJueves = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/3"); // jueves
        File pathViernes = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/4"); // viernes
        File pathAgenda = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/11a"); // agenda

        pathSettings.mkdirs();
        pathAsign.mkdirs();
        pathInfoUser.mkdirs();
        pathNotas.mkdirs();
        pathTareas.mkdirs();
        pathLunes.mkdirs();
        pathMartes.mkdirs();
        pathMiercoles.mkdirs();
        pathJueves.mkdirs();
        pathViernes.mkdirs();
        pathAgenda.mkdirs();

        Write.WriteInfoApp(diaActual, check, true);

    }

}
