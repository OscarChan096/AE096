package com.studio.oscar.agendaescolar.Edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.Objetos.homework;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class EditTareas extends AppCompatActivity implements View.OnClickListener {

    EditText inTarea, fechaEntrega;
    Spinner asignaturaOpcion;
    String posicion, asignatura;

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.editar_tareas);

        asignaturaOpcion = findViewById(R.id.spinner);
        inTarea = findViewById(R.id.inTarea);
        fechaEntrega = findViewById(R.id.fechaEntrega);
        Button btnSave = findViewById(R.id.btnguardar);

        Intent i = getIntent();
        asignatura = i.getStringExtra("asign");
        String tarea = i.getStringExtra("tarea");
        String fecha_entrega = i.getStringExtra("entrega");
        posicion = i.getStringExtra("posicion");

        leerAsignaturas();
        inTarea.setText(tarea);
        fechaEntrega.setText(fecha_entrega);

        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int v = view.getId();

        String spinnerOpcion = asignaturaOpcion.getItemAtPosition(asignaturaOpcion.getSelectedItemPosition()).toString();
        String tareaStr = inTarea.getText().toString();
        String fechaStr = fechaEntrega.getText().toString();

        if (tareaStr.length() == 0) {
            Toast.makeText(getApplicationContext(), "No se han ingresado datos en tarea", Toast.LENGTH_SHORT).show();
        } else {
            switch (v) {
                case R.id.btnguardar:

                    try {
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8");
                        File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8").listFiles();
                        short pos = Short.parseShort(posicion);
                        String name = arrayFile[pos].getName(); //nfile
                        File fileName = new File(path.getAbsolutePath(), name);
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        fechaStr = (fechaStr.length() == 0) ? "Sin especificar" : fechaStr;

                        homework hm = new homework(spinnerOpcion, tareaStr, fechaStr);
                        salida.writeObject(hm);

                        Toast.makeText(getApplicationContext(), "Agregado con exito", Toast.LENGTH_SHORT).show();

                    } catch (FileNotFoundException fnfe) {
                        Toast.makeText(getApplicationContext(), "archivo no encontrado: " + fnfe.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException ioe) {
                        Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                    }
                    String[] strArray = {"sin seleccion"};
                    asignaturaOpcion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, strArray));
                    inTarea.setText("");
                    fechaEntrega.setText("");
                    break;
            }
        }
    } // end onClick

    void leerAsignaturas() {

        short nFile;

        String name;
        int pos = 0;
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7"); // obtiene el acceso a la memoria interna y obtiene el directorio
        File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles(); // obtiene la lista de archivos que existen en el directorio
        String[] arrayStr = new String[arrayFile.length];

        for (nFile = 0; nFile <= arrayStr.length; nFile++) {
            try {
                name = arrayFile[nFile].getName();
                File fileName = new File(path.getAbsolutePath(), name);
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                HClases aux = (HClases) entrada.readObject();
                String asign = aux.getAsignatura();

                arrayStr[nFile] = asign;

                if (asign.equals(asignatura)) {
                    pos = nFile;
                }

            } catch (FileNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "Archivo no encontrado: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
            } catch (ArrayIndexOutOfBoundsException e) {
                //Toast.makeText(getApplicationContext(), "Error Array: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } // end for
        asignaturaOpcion.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayStr));
        asignaturaOpcion.setSelection(pos);

    } // end leerAsignaturas

}
