package com.studio.oscar.agendaescolar.Edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Dias.LectorClases;
import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class EditarAsignaturas extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.edit_asign_days);
        final EditText asign = findViewById(R.id.nameAsign);
        final EditText hora = findViewById(R.id.hora);
        final EditText tohr = findViewById(R.id.tohoradays);
        final EditText profesor = findViewById(R.id.profesor);
        final EditText aula = findViewById(R.id.aula);
        Button btn = findViewById(R.id.btnGuardarAsign);

        Intent i = getIntent();
        String asignx = i.getStringExtra("asign");
        String profesorx = i.getStringExtra("profesor");
        String aulax = i.getStringExtra("aula");
        String horax = i.getStringExtra("hora");
        String tohrx = i.getStringExtra("tohr");
        final String clavehr = i.getStringExtra("clavehr");
        name = i.getStringExtra("nombrearchivo");
        final String ID = i.getStringExtra("id");
        final String dia = i.getStringExtra("dia");
        final String title = i.getStringExtra("title");

        asign.setText(asignx);
        hora.setText(horax);
        tohr.setText(tohrx);
        profesor.setText(profesorx);
        aula.setText(aulax);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asigny = "Asignatura: "+asign.getText().toString();
                String profesory =profesor.getText().toString();
                String aulay = aula.getText().toString();
                String horay = hora.getText().toString();
                String tohry = tohr.getText().toString();
                switch (ID){
                    case "0": lunes(Integer.parseInt(String.valueOf(clavehr)),asigny, profesory, aulay, horay, tohry); break;
                    case "1": martes(Integer.parseInt(String.valueOf(clavehr)),asigny, profesory, aulay, horay, tohry); break;
                    case "2": miercoles(Integer.parseInt(String.valueOf(clavehr)),asigny, profesory, aulay, horay, tohry); break;
                    case "3": jueves(Integer.parseInt(String.valueOf(clavehr)),asigny, profesory, aulay, horay, tohry); break;
                    case "4": viernes(Integer.parseInt(String.valueOf(clavehr)),asigny, profesory, aulay, horay, tohry); break;
                }
                Intent home_intent = new Intent(getApplicationContext(),
                        LectorClases.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home_intent.putExtra("dia",dia);
                home_intent.putExtra("title",title);
                startActivity(home_intent);
            }
        });
    }

    void lunes(int clavehr, String a, String p, String au, String hora, String tohr){

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/0");
            File fileName = new File(path.getAbsolutePath(), name);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            p = (p.length() == 0)?"Docente: Sin especificar":"Docente: "+p;
            au = (au.length() == 0)?"Aula: Sin especificar":"Aula: "+au;

            HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
            vi.setNombreArchivo(name);
            salida.writeObject(vi);

            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
        }
    }

    void martes(int clavehr, String a, String p, String au, String hora, String tohr){

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/1");
            File fileName = new File(path.getAbsolutePath(), name);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            p = (p.length() == 0)?"Docente: Sin especificar":"Docente: "+p;
            au = (au.length() == 0)?"Aula: Sin especificar":"Aula: "+au;

            HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
            vi.setNombreArchivo(name);
            salida.writeObject(vi);

            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
        }
    }

    void miercoles(int clavehr, String a, String p, String au, String hora, String tohr){
        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/2");
            File fileName = new File(path.getAbsolutePath(), name);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            p = (p.length() == 0)?"Docente: Sin especificar":"Docente: "+p;
            au = (au.length() == 0)?"Aula: Sin especificar":"Aula: "+au;

            HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
            vi.setNombreArchivo(name);
            salida.writeObject(vi);

            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
        }
    }

    void  jueves(int clavehr, String a, String p, String au, String hora, String tohr){

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/3");
            File fileName = new File(path.getAbsolutePath(), name);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            p = (p.length() == 0)?"Docente: Sin especificar":"Docente: "+p;
            au = (au.length() == 0)?"Aula: Sin especificar":"Aula: "+au;

            HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
            vi.setNombreArchivo(name);
            salida.writeObject(vi);

            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
        }
    }

    void viernes(int clavehr, String a, String p, String au, String hora, String tohr){

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/4");
            File fileName = new File(path.getAbsolutePath(), name);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            p = (p.length() == 0)?"Docente: Sin especificar":"Docente: "+p;
            au = (au.length() == 0)?"Aula: Sin especificar":"Aula: "+au;

            HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
            vi.setNombreArchivo(name);
            salida.writeObject(vi);

            Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Toast.makeText(getApplicationContext(), "Error IOE", Toast.LENGTH_SHORT).show();
        }
    }

} // end class
