package com.studio.oscar.agendaescolar.Add;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Datos.ConversionObj;
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

public class AddTareas extends AppCompatActivity {

    EditText inTarea, fechaEntrega;
    View btnSave;
    Spinner asignaturaOpcion;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tareas);

        asignaturaOpcion = findViewById(R.id.spinner);
        inTarea = findViewById(R.id.inTarea);
        fechaEntrega = findViewById(R.id.fechaEntrega);

        leerAsignaturas();

        btnSave = findViewById(R.id.btnguardar);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String spinnerOpcion = asignaturaOpcion.getItemAtPosition(asignaturaOpcion.getSelectedItemPosition()).toString();
                String tarea = inTarea.getText().toString();
                String entrega = fechaEntrega.getText().toString();
                guardarTareas(spinnerOpcion,tarea,entrega);
            }
        });
        //createSingleListDialog();
    }

    void leerAsignaturas() {

        short nFile;
        String name;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7"); // obtiene el acceso a la memoria interna y obtiene el directorio
        File[] arrayFilex = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles();
        File fileName = new File(path.getAbsolutePath(), name = arrayFilex[0].getName());
        if(path.isDirectory() && fileName.exists()){
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles(); // obtiene la lista de archivos que existen en el directorio
            String[] arrayStr  = new String[arrayFile.length];
            if(arrayFile != null){
                for(nFile = 0; nFile <= arrayStr.length; nFile++){
                    try{
                        name = arrayFile[nFile].getName();
                        fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        HClases aux = (HClases) entrada.readObject();
                        String asign = aux.getAsignatura();

                        arrayStr[nFile] = asign;

                    }catch(FileNotFoundException ex){
                        Toast.makeText(getApplicationContext(),"Archivo no encontrado: "+ex.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException e){
                        Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                    }catch (ArrayIndexOutOfBoundsException e){}
                     catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } // end for
                asignaturaOpcion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayStr));
            }
        }else if(path.isDirectory() && !fileName.exists() ){
            String[] strArray = {"No se han agregado asignaturas"};
            asignaturaOpcion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, strArray));
        }else{
            String[] strArray = {"No se han agregado asignaturas"};
            asignaturaOpcion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, strArray));
        }
    } // end leerAsignaturas


    public void guardarTareas(String asignatura, String tarea, String entrega){
        String work = "tarea";
        String ext = ".tr";
        String name = work+ext;
        if(!asignatura.equals("No se han agregado asignaturas")) {
            try {
                File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8");
                File fileName = new File(path.getAbsolutePath(), name);
                if (path.isDirectory() && fileName.exists()) {
                    File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8").listFiles();

                    int next = arrayFile.length;

                    name = work + "(" + next + ")" + ext; //nfile
                    fileName = new File(path.getAbsolutePath(), name);
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                    entrega = (entrega.length() == 0) ? "Sin especificar" : entrega;

                    homework hm = new homework(asignatura, tarea, entrega);
                    salida.writeObject(hm);

                    Toast.makeText(getApplicationContext(), "Agregado con exito", Toast.LENGTH_SHORT).show();

                } else if (path.isDirectory()) {
                    File fileNameX = new File(path.getAbsolutePath(), name);
                    FileOutputStream fileOut = new FileOutputStream(fileNameX);
                    ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                    entrega = (entrega.length() == 0) ? "Sin especificar" : entrega;

                    homework hm = new homework(asignatura, tarea, entrega);
                    salida.writeObject(hm);

                    Toast.makeText(getApplicationContext(), "Agregado con exito", Toast.LENGTH_SHORT).show();
                }/* else {
                    path.mkdirs(); // crea la carpeta
                    File fileNameX = new File(path.getAbsolutePath(), name);
                    FileOutputStream fileOut = new FileOutputStream(fileNameX);
                    ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                    entrega = (entrega.length() == 0) ? "Sin especificar" : entrega;

                    homework hm = new homework(asignatura, tarea, entrega);
                    salida.writeObject(hm);

                    Toast.makeText(getApplicationContext(), "Agregado con exito", Toast.LENGTH_SHORT).show();
                }*/

            } catch (FileNotFoundException fnfe) {
                Toast.makeText(getApplicationContext(), "archivo no encontrado: " + fnfe.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException ioe) {
                Snackbar.make(findViewById(android.R.id.content), "Error: " + ioe.getMessage(), Snackbar.LENGTH_LONG);
            }
            inTarea.setText("");
            fechaEntrega.setText("");
            Toast.makeText(getApplicationContext(), "Tarea guardada con exito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"No existen asignaturas", Toast.LENGTH_SHORT).show();
        }
        ConversionObj.ConverterToAgenda();

    }

}
