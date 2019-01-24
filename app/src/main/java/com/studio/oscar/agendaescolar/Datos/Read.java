package com.studio.oscar.agendaescolar.Datos;

import com.studio.oscar.agendaescolar.Objetos.Evt;
import com.studio.oscar.agendaescolar.Objetos.Nota;
import com.studio.oscar.agendaescolar.Objetos.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class Read {

    public static ArrayList ReadTareas() {
        ArrayList<homework> arrayList = new ArrayList<>();
        String name;
        homework hm;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if (path.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        homework aux = (homework) entrada.readObject();
                        String asign = "Asignatura: " + aux.getAsignatura();
                        String tarea = aux.getTarea();
                        String fechaEntrega = "Fecha de entrega: " + aux.getFechaEntrega();

                        hm = new homework(asign, tarea, fechaEntrega);
                        arrayList.add(hm);

                    } catch (FileNotFoundException ex) {
                    } catch (IOException e) {
                    } catch (ArrayIndexOutOfBoundsException e) {
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
            }
        }
        return arrayList;
    }

    public static ArrayList ReadNotas() {
        String name;
        ArrayList<Nota> arrayList = new ArrayList<>();
        Nota n;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if (path.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        Nota aux = (Nota) entrada.readObject();
                        String text = aux.getText();

                        n = new Nota(text);
                        arrayList.add(n);

                    } catch (FileNotFoundException ex) {
                    } catch (IOException e) {
                    } catch (ArrayIndexOutOfBoundsException e) {
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
            }
        }

        return arrayList;
    }

    public static ArrayList ReadEventos() {
        String name;
        ArrayList<Evt> arrayList = new ArrayList<>();
        Evt e;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/10"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if (path.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/10").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        Evt aux = (Evt) entrada.readObject();
                        String nameEvt = "Nombre del evento: " + aux.getNameEvt();
                        String desc = aux.getDescripcion();
                        String fecha = "Fecha: " + aux.getFecha();
                        String hora = "Hora: " + aux.getHora();

                        e = new Evt(nameEvt, desc, fecha, hora);
                        arrayList.add(e);

                    } catch (FileNotFoundException ex) {
                    } catch (IOException ex) {
                    } catch (ArrayIndexOutOfBoundsException ex) {
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } // end for
            }
        }
        return arrayList;
    }

}
