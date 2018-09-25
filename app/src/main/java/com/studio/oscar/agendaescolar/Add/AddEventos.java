package com.studio.oscar.agendaescolar.Add;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Objetos.Evt;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class AddEventos extends AppCompatActivity implements View.OnClickListener{

    EditText nombreEvento, descripcion, fecha, hora;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_eventos);

        nombreEvento = (EditText)findViewById(R.id.nombre_evento);
        descripcion = (EditText)findViewById(R.id.descripcion_evento);
        fecha = (EditText)findViewById(R.id.fecha_evento);
        hora = (EditText)findViewById(R.id.hora_evento);

        View btnGuardar = findViewById(R.id.guardar_evt);
        btnGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int id = view.getId();

        String nameEvt = nombreEvento.getText().toString();
        String desc = descripcion.getText().toString();
        String fechaEvt = fecha.getText().toString();
        String hr = hora.getText().toString();

        if(nameEvt.length() == 0 && fechaEvt.length() == 0 && hr.length() == 0){
            Toast.makeText(getApplicationContext(),"No se han agregado datos en nombre, hora y fecha ", Toast.LENGTH_LONG).show();
        }else if (nameEvt.length() == 0 && fechaEvt.length() == 0){
            Toast.makeText(getApplicationContext(),"No se han agregado datos en nombre y fecha ", Toast.LENGTH_LONG).show();
        }else if (fechaEvt.length() == 0 || fechaEvt.length() == 0){
            Toast.makeText(getApplicationContext(),"No se han agregado datos en hora y fecha ", Toast.LENGTH_LONG).show();
        }else if (nameEvt.length() == 0 && hr.length() == 0){
            Toast.makeText(getApplicationContext(),"No se han agregado datos en nombre y hora ", Toast.LENGTH_LONG).show();
        }else {
            switch (id){
                case R.id.guardar_evt:
                    String evtx = "eventos";
                    String ext = ".evt";
                    String name = evtx+ext;

                    try{
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/10");
                        File fileName = new File(path.getAbsolutePath(), name);
                        if(path.isDirectory() && fileName.exists()){
                            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/10").listFiles();

                            int next = arrayFile.length;

                            name = evtx+"("+next+")"+ext; //nfile
                            fileName = new File(path.getAbsolutePath(), name);
                            FileOutputStream fileOut = new FileOutputStream(fileName);
                            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                            desc = (desc.length() == 0)?"Sin especificar":desc;

                            Evt e = new Evt(nameEvt,desc,fechaEvt,hr);
                            salida.writeObject(e);

                            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

                        }else if(path.isDirectory()){
                            File fileNameX = new File(path.getAbsolutePath(), name);
                            FileOutputStream fileOut = new FileOutputStream(fileNameX);
                            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                            desc = (desc.length() == 0)?"Sin especificar":desc;

                            Evt e = new Evt(nameEvt,desc,fechaEvt,hr);
                            salida.writeObject(e);

                            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
                        }else{
                            path.mkdirs(); // crea la carpeta
                            File fileNameX = new File(path.getAbsolutePath(), name);
                            FileOutputStream fileOut = new FileOutputStream(fileNameX);
                            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                            desc = (desc.length() == 0)?"Sin especificar":desc;

                            Evt e = new Evt(nameEvt,desc,fechaEvt,hr);
                            salida.writeObject(e);

                            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
                        }

                    }catch(FileNotFoundException fnfe){
                        Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException ioe){
                        Snackbar.make(findViewById(android.R.id.content),"Error: "+ioe.getMessage(), Snackbar.LENGTH_LONG);
                    }
                    nombreEvento.setText("");
                    descripcion.setText("");
                    fecha.setText("");
                    hora.setText("");
                    break;
            }
        }
    }

}
