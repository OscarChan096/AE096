package com.studio.oscar.agendaescolar.Add;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Excepciones.IOExcepcionHora;
import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class AddHora extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.add_hora);

        Intent i = getIntent();
        final String ID = i.getStringExtra("id");
        final String asign = i.getStringExtra("asign");
        final String profesor = i.getStringExtra("profesor");
        final String aula = i.getStringExtra("aula");
        final String title = i.getStringExtra("title");

        final EditText hora = (EditText)findViewById(R.id.hora);
        final EditText tohr = (EditText)findViewById(R.id.tohr);
        Button btn = (Button)findViewById(R.id.btnsavehr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int clavehr;
                    String hr = hora.getText().toString();
                    String ahr = tohr.getText().toString();
                    if (VerificarHora(hr, ahr)) {
                        if (hr.length() > 4) {
                            char decena = hr.charAt(0);
                            char unidad = hr.charAt(1);
                            clavehr = Integer.parseInt(String.valueOf(decena + "" + unidad));
                            if(unidad == ':')
                                throw new IOExcepcionHora("Error en la hora");
                        } else {
                            char unidad = hr.charAt(0);
                            clavehr = Integer.parseInt(String.valueOf(unidad));
                        }
                        Snackbar.make(findViewById(android.R.id.content), "id: " + clavehr, Snackbar.LENGTH_LONG).show();
                        switch (Integer.parseInt(ID)) {
                            case 0:
                                lunes(clavehr, asign, profesor, aula, hr, ahr);
                                break;
                            case 1:
                                martes(clavehr, asign, profesor, aula, hr, ahr);
                                break;
                            case 2:
                                miercoles(clavehr, asign, profesor, aula, hr, ahr);
                                break;
                            case 3:
                                jueves(clavehr, asign, profesor, aula, hr, ahr);
                                break;
                            case 4:
                                viernes(clavehr, asign, profesor, aula, hr, ahr);
                                break;
                        }
                        Intent intent = new Intent(AddHora.this, AddToDay.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id", ID);
                        intent.putExtra("title", title);
                        startActivity(intent);
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Hora incorrecta", Snackbar.LENGTH_LONG).show();
                    }
                }catch(IOExcepcionHora ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void lunes(int clavehr, String a, String p, String au, String hora, String tohr){
        String horario = "lunes";
        String ext = ".hr";
        String name = horario+ext;

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/0");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/0").listFiles();

                int next = arrayFile.length;

                name = horario+"("+next+")"+ext; //nfile
                fileName = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);

                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

            }else if(path.isDirectory()){
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);

                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);

                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivos no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Snackbar.make(findViewById(android.R.id.content), ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    void martes(int clavehr, String a, String p, String au, String hora, String tohr){
        String horario = "martes";
        String ext = ".hr";
        String name = horario+ext;

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/1");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/1").listFiles();

                int next = arrayFile.length;

                name = horario+"("+next+")"+ext; //nfile
                fileName = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);

                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

            }else if(path.isDirectory()){
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivos no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Snackbar.make(findViewById(android.R.id.content), ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    void miercoles(int clavehr, String a, String p, String au, String hora, String tohr){
        String horario = "miercoles";
        String ext = ".hr";
        String name = horario+ext;

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/2");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/2").listFiles();

                int next = arrayFile.length;

                name = horario+"("+next+")"+ext; //nfile
                fileName = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

            }else if(path.isDirectory()){
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivos no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Snackbar.make(findViewById(android.R.id.content), ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    void jueves(int clavehr, String a, String p, String au, String hora, String tohr){
        String horario = "jueves";
        String ext = ".hr";
        String name = horario+ext;

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/3");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/3").listFiles();

                int next = arrayFile.length;

                name = horario+"("+next+")"+ext; //nfile
                fileName = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

            }else if(path.isDirectory()){
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivos no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Snackbar.make(findViewById(android.R.id.content), ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    void viernes(int clavehr, String a, String p, String au, String hora, String tohr){
        String horario = "viernes";
        String ext = ".hr";
        String name = horario+ext;

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/4");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/4").listFiles();

                int next = arrayFile.length;

                name = horario+"("+next+")"+ext; //nfile
                fileName = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

            }else if(path.isDirectory()){
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivos no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Snackbar.make(findViewById(android.R.id.content), ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    private boolean VerificarHora(String hr, String toHr){
        if((hr.length() > 3 && hr.length() < 6) && (toHr.length() > 3 && toHr.length() < 6)){
            return true;
        }else{
            return false;
        }
    }

} // end class
