package com.studio.oscar.agendaescolar.Edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Objetos.Nota;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class EditarNotas extends AppCompatActivity implements View.OnClickListener{

    EditText boxTextNotas;
    View btnSave;
    String posicion;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.editar_notas);

        boxTextNotas = findViewById(R.id.inTarea);
        btnSave = findViewById(R.id.btnguardar);

        Intent i = getIntent();
        String text = i.getStringExtra("text");
        posicion = i.getStringExtra("posicion");

        boxTextNotas.setText(text);

        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int v = view.getId();

        String textStr = boxTextNotas.getText().toString();

        if(textStr.length() == 0){
            Toast.makeText(getApplicationContext(),"No se han ingresado datos", Toast.LENGTH_SHORT).show();
        }else{
            switch (v){
                case R.id.btnguardar:

                    try{
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9");
                        File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9").listFiles();
                        short pos = Short.parseShort(posicion);
                        String name = arrayFile[pos].getName(); //nfile
                        File fileName = new File(path.getAbsolutePath(), name);
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        Nota n = new Nota(textStr);
                        salida.writeObject(n);

                        Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

                    }catch(FileNotFoundException fnfe){
                        Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException ioe){
                        Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
                    }
                    boxTextNotas.setText("");
                    break;
            }
        }
    } // end onClick

}
