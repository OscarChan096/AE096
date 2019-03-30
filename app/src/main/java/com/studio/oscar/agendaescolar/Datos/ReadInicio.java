package com.studio.oscar.agendaescolar.Datos;


import com.studio.oscar.agendaescolar.Objetos.Inf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class ReadInicio {

    public static boolean In(String user, String password) {
        boolean bol = false;
        String obj = "0";
        String ext = ".usp";
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/00"); // obtiene el acceso a la memoria interna y obtiene el directorio
        String name = obj+ext;
        File fileName = new File(path.getAbsolutePath(), name);
        if (path.isDirectory() && fileName.exists()) {
            try {
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                Inf aux = (Inf) entrada.readObject();
                String userx = aux.getUser();
                String passwordx = aux.getPassword();

                bol = (userx.equals(user) && passwordx.equals(password))?true:false;

            }
            catch (FileNotFoundException ex) {}
            catch (IOException e) {}
            catch (ArrayIndexOutOfBoundsException e) {}
            catch (ClassNotFoundException e) {}
        }else {
            bol = true;
        }

        return bol;
    }

}
