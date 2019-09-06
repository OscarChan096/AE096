package com.studio.oscar.agendaescolar.Datos;

import android.util.Log;

import com.studio.oscar.agendaescolar.Objetos.Agenda;
import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.Objetos.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class ConversionObj {

    final static String lunes = "lunes";
    final static String martes = "martes";
    final static String miercoles = "miercoles";
    final static String jueves = "jueves";
    final static String viernes = "viernes";
    private ArrayList<Agenda> listAgenda;

    public static void ConverterToAgenda() {
        ConversionObj conversionObj = new ConversionObj();
        conversionObj.listAgenda = new ArrayList<>();
        conversionObj.writeListAgenda();
    }

    private void writeListAgenda() {
        String dia = ReadDate.getDia();
        switch (dia) {
            case "lunes":
                xReadLunes();
                xReadMartes();
                break;
            case "martes":
                xReadMartes();
                xReadMiercoles();
                break;
            case "miercoles":
                xReadMiercoles();
                xReadJueves();
                break;
            case "jueves":
                xReadJueves();
                xReadViernes();
                break;
            case "viernes":
                xReadViernes();
                xReadLunes();
                break;
        }
        xReadTareas();
        Log.d("ConversorObjArraySize",listAgenda.size()+"");
        try {
            String name = "agenda.ag";
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/11a");
            if (path.isDirectory()) {
                Log.d("Directorio","existe el directorio");
                File fileName = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                salida.writeObject(listAgenda);
            }else{
                path.mkdirs();
                Log.d("Directorio","no existe el directorio");
                File fileName = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                salida.writeObject(listAgenda);
            }

        } catch (FileNotFoundException fnfe) {
            Log.d("FileNotFoundException",fnfe.getMessage());
        } catch (IOException ioe) {
            Log.d("IOException",ioe.getMessage());
        }
    }

    private void xReadLunes() {
        ArrayList<HClases> list = ReadDay.Read(0);
        for (int i = 0; i < list.size(); i++) {
            HClases aux = list.get(i);
            Agenda agenda = new Agenda(aux.getId(), aux.getAsignatura(), "", (aux.getAula().length() == 0)?"Sin especificar":aux.getAula(), aux.getHora(), aux.getTohora(), lunes, "Horario");
            listAgenda.add(agenda);
        }
    }

    private void xReadMartes() {
        ArrayList<HClases> list = ReadDay.Read(1);
        for (int i = 0; i < list.size(); i++) {
            HClases aux = list.get(i);
            Agenda agenda = new Agenda(aux.getId(), aux.getAsignatura(), "", (aux.getAula().length() == 0)?"Sin especificar":aux.getAula(), aux.getHora(), aux.getTohora(), martes, "Horario");
            listAgenda.add(agenda);
        }
    }

    private void xReadMiercoles() {
        ArrayList<HClases> list = ReadDay.Read(2);
        for (int i = 0; i < list.size(); i++) {
            HClases aux = list.get(i);
            Agenda agenda = new Agenda(aux.getId(), aux.getAsignatura(), "", (aux.getAula().length() == 0)?"Sin especificar":aux.getAula(), aux.getHora(), aux.getTohora(), miercoles, "Horario");
            listAgenda.add(agenda);
        }
    }

    private void xReadJueves() {
        ArrayList<HClases> list = ReadDay.Read(3);
        for (int i = 0; i < list.size(); i++) {
            HClases aux = list.get(i);
            Agenda agenda = new Agenda(aux.getId(), aux.getAsignatura(), "", (aux.getAula().length() == 0)?"Sin especificar":aux.getAula(), aux.getHora(), aux.getTohora(), jueves, "Horario");
            listAgenda.add(agenda);
        }
    }

    private void xReadViernes() {
        ArrayList<HClases> list = ReadDay.Read(4);
        for (int i = 0; i < list.size(); i++) {
            HClases aux = list.get(i);
            Agenda agenda = new Agenda(aux.getId(), aux.getAsignatura(), "", (aux.getAula().length() == 0)?"Sin especificar":aux.getAula(), aux.getHora(), aux.getTohora(), viernes, "Horario");
            listAgenda.add(agenda);
        }
    }

    private void xReadTareas() {
        ArrayList<homework> list = Read.ReadTareas();
        for (int i = 0; i < list.size(); i++) {
            homework aux = list.get(i);
            Agenda agenda = new Agenda(aux.getAsignatura(), aux.getTarea(), aux.getFechaEntrega(), "Tareas");
            agenda.setHora("");
            agenda.setTohora("");
            listAgenda.add(agenda);
        }
    }

} // end class
