package com.studio.oscar.agendaescolar.Edit;

import android.content.Intent;
import android.os.Bundle;
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

public class EditarEventos extends AppCompatActivity implements View.OnClickListener{

    EditText nombreEvento, descripcion, fecha, hora;
    View btnGuardar;
    String posicion;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.editar_eventos);

        nombreEvento = findViewById(R.id.nombre_evento);
        descripcion = findViewById(R.id.descripcion_evento);
        fecha = findViewById(R.id.fecha_evento);
        hora = findViewById(R.id.hora_evento);
        btnGuardar = findViewById(R.id.guardar_evt);

        Intent i = getIntent();
        String nameEvt = i.getStringExtra("evt");
        String descr = i.getStringExtra("des");
        String strFecha = i.getStringExtra("fecha");
        String strHora = i.getStringExtra("hora");
        posicion = i.getStringExtra("posicion");

        nombreEvento.setText(nameEvt);
        descripcion.setText(descr);
        fecha.setText(strFecha);
        hora.setText(strHora);

        btnGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int v = view.getId();

        String nameStr = nombreEvento.getText().toString();
        String descrip = descripcion.getText().toString();
        String fechaStr = fecha.getText().toString();
        String hr = hora.getText().toString();

        if(nameStr.equals("") && hr.equals("")){
            Toast.makeText(getApplicationContext(),"No se han ingresado datos en nombre de evento y hora de evento", Toast.LENGTH_SHORT).show();
        }else if(nameStr.equals("") || nameStr.equals(" ")){
            Toast.makeText(getApplicationContext(),"No se ha ingresado el nombre de la asignatura",Toast.LENGTH_SHORT).show();
        }else if(hr.equals("")){
            Toast.makeText(getApplicationContext(),"No se ha ingresado la hora de la clase",Toast.LENGTH_SHORT).show();
        }else{
            switch (v){
                case R.id.guardar_evt:

                    try{
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/10");
                        File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/10").listFiles();
                        short pos = Short.parseShort(posicion);
                        String name = arrayFile[pos].getName(); //nfile
                        File fileName = new File(path.getAbsolutePath(), name);
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        Evt vi = new Evt(nameStr,descrip,fechaStr,hr);
                        salida.writeObject(vi);

                        Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

                    }catch(FileNotFoundException fnfe){
                        Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException ioe){
                        Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                    }
                    nombreEvento.setText("");
                    descripcion.setText("");
                    fecha.setText("");
                    hora.setText("");
                    break;
            }
        }
    } // end onClick

}
