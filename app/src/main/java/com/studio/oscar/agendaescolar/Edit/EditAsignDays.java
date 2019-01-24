package com.studio.oscar.agendaescolar.Edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class EditAsignDays extends AppCompatActivity {
    EditText nameAsign, etProfesor, etAula;
    View btnSave;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.editar_asignaturas);
        nameAsign = findViewById(R.id.nameAsign);
        etProfesor = findViewById(R.id.profesor);
        etAula = findViewById(R.id.aula);
        btnSave = findViewById(R.id.btnGuardarAsign);

        Intent i = getIntent();
        String asign = i.getStringExtra("asign");
        String profesor = i.getStringExtra("profesor");
        String aula = i.getStringExtra("aula");
        final String posicion = i.getStringExtra("posicion");

        nameAsign.setText(asign);
        etProfesor.setText(profesor);
        etAula.setText(aula);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asignx = nameAsign.getText().toString();
                String profesorx = etProfesor.getText().toString();
                String aulax = etAula.getText().toString();

                try{
                    File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7");
                    File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles();
                    short pos = Short.parseShort(posicion);
                    String name = arrayFile[pos].getName(); //nfile
                    File fileName = new File(path.getAbsolutePath(), name);
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                    profesorx = (profesorx.length() == 0)?"Sin especificar":profesorx;
                    aulax = (aulax.length() == 0)?"Sin especificar":aulax;

                    HClases vi = new HClases(asignx,profesorx,aulax);
                    salida.writeObject(vi);

                    Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

                }catch(FileNotFoundException fnfe){
                    Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
                }catch(IOException ioe){
                    Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
