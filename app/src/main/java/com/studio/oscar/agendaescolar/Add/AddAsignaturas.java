package com.studio.oscar.agendaescolar.Add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.MainActivity;
import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class AddAsignaturas extends AppCompatActivity implements View.OnClickListener {

    View btnGuardar;
    EditText nameAsign,docente,aula;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.add_asignaturas);

        nameAsign = (EditText)findViewById(R.id.nameAsign);
        docente = (EditText)findViewById(R.id.profesor);
        aula = (EditText)findViewById(R.id.aula);

        btnGuardar = findViewById(R.id.btnGuardarAsign);
        btnGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int v = view.getId();

        String asign = nameAsign.getText().toString();
        String profesor = docente.getText().toString();
        String aulaX = aula.getText().toString();

        if(asign.length() == 0){
            Toast.makeText(getApplicationContext(),"No se ha ingresado el nombre de la asignatura", Toast.LENGTH_SHORT).show();
        }else{
            switch (v) {
                case R.id.btnGuardarAsign:

                    String horario = "horario";
                    String obj = ".obj";
                    String name = horario+obj;

                    try{
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7");
                        File fileName = new File(path.getAbsolutePath(), name);
                        if(path.isDirectory() && fileName.exists()){
                            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles();

                            int next = arrayFile.length;

                            name = horario+"("+next+")"+obj; //nfile
                            fileName = new File(path.getAbsolutePath(), name);
                            FileOutputStream fileOut = new FileOutputStream(fileName);
                            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                            profesor = (profesor.length() == 0)?"Sin especificar":profesor;
                            aulaX = (aulaX.length() == 0)?"Sin especificar":aulaX;

                            HClases vi = new HClases(asign,profesor,aulaX);

                            salida.writeObject(vi);

                            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

                        }else if(path.isDirectory()){
                            File fileNameX = new File(path.getAbsolutePath(), name);
                            FileOutputStream fileOut = new FileOutputStream(fileNameX);
                            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                            profesor = (profesor.length() == 0)?"Sin especificar":profesor;
                            aulaX = (aulaX.length() == 0)?"Sin especificar":aulaX;
                            HClases vi = new HClases(asign,profesor,aulaX);

                            salida.writeObject(vi);

                            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
                        }else{
                            path.mkdirs(); // crea la carpeta
                            File fileNameX = new File(path.getAbsolutePath(), name);
                            FileOutputStream fileOut = new FileOutputStream(fileNameX);
                            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                            profesor = (profesor.length() == 0)?"Sin especificar":profesor;
                            aulaX = (aulaX.length() == 0)?"Sin especificar":aulaX;
                            HClases vi = new HClases(asign,profesor,aulaX);

                            salida.writeObject(vi);

                            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
                        }

                    }catch(FileNotFoundException fnfe){
                        Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
                    }catch(IOException ioe){}

                    nameAsign.setText("");
                    docente.setText("");
                    aula.setText("");
                    break;
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    } // end onClick

}
