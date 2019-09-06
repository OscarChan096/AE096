package com.studio.oscar.agendaescolar.Datos;

import com.studio.oscar.agendaescolar.Objetos.InfoApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class Write {

    public static void WriteInfoApp(String diaActual, boolean check) {
        String name = "infoapp.app";
        try {
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/12x");
            File fileName = new File(path.getAbsolutePath(), name);

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            InfoApp infoApp = new InfoApp();
            infoApp.setDiaSistema(diaActual);
            infoApp.setCheck(check);

            salida.writeObject(infoApp);


        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        }
    }

}
