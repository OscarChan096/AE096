package com.studio.oscar.agendaescolar.Add;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Objetos.Usuario;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class AddInfoUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.add_info_user);
        final EditText nombre = findViewById(R.id.nombre_info_user);
        final EditText escuela = findViewById(R.id.escuela_info_user);
        final EditText especialidad = findViewById(R.id.especialidad_info_user);
        final EditText numTel = findViewById(R.id.numtel_info_user);
        final EditText nControl = findViewById(R.id.ncontrol_info_user);
        Button btn = findViewById(R.id.btn_info_user);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombrex = nombre.getText().toString();
                String escuelax = escuela.getText().toString();
                String especialidadx = especialidad.getText().toString();
                String numTelx = numTel.getText().toString();
                String nControlx = nControl.getText().toString();

                String evtx = "infoUser";
                String ext = ".inf";
                String name = evtx+ext;

                try{
                    File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf");
                    File fileName = new File(path.getAbsolutePath(), name);
                    if(path.isDirectory() && fileName.exists()){
                        File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf").listFiles();

                        int next = arrayFile.length;

                        name = evtx+"("+next+")"+ext; //nfile
                        fileName = new File(path.getAbsolutePath(), name);
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        escuelax = (escuelax.length() == 0)?"Sin especificar":escuelax;
                        especialidadx = (especialidadx.length() == 0)?"Sin especificar":especialidadx;
                        numTelx = (numTelx.length() == 0)?"Sin especificar":numTelx;
                        nControlx = (nControlx.length() == 0)?"Sin especificar":nControlx;

                        Usuario user = new Usuario(nombrex,escuelax,especialidadx,numTelx,nControlx);
                        salida.writeObject(user);

                        Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();

                    }else if(path.isDirectory()){
                        File fileNameX = new File(path.getAbsolutePath(), name);
                        FileOutputStream fileOut = new FileOutputStream(fileNameX);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        escuelax = (escuelax.length() == 0)?"Sin especificar":escuelax;
                        especialidadx = (especialidadx.length() == 0)?"Sin especificar":especialidadx;
                        numTelx = (numTelx.length() == 0)?"Sin especificar":numTelx;
                        nControlx = (nControlx.length() == 0)?"Sin especificar":nControlx;

                        Usuario user = new Usuario(nombrex,escuelax,especialidadx,numTelx,nControlx);
                        salida.writeObject(user);

                        Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
                    }/*else{
                        path.mkdirs(); // crea la carpeta
                        File fileNameX = new File(path.getAbsolutePath(), name);
                        FileOutputStream fileOut = new FileOutputStream(fileNameX);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        escuelax = (escuelax.length() == 0)?"Sin especificar":escuelax;
                        especialidadx = (especialidadx.length() == 0)?"Sin especificar":especialidadx;
                        numTelx = (numTelx.length() == 0)?"Sin especificar":numTelx;
                        nControlx = (nControlx.length() == 0)?"Sin especificar":nControlx;

                        Usuario user = new Usuario(nombrex,escuelax,especialidadx,numTelx,nControlx);
                        salida.writeObject(user);

                        Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
                    }*/

                }catch(FileNotFoundException fnfe){
                    Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
                }catch(IOException ioe){
                    Snackbar.make(findViewById(android.R.id.content),"Error: "+ioe.getMessage(), Snackbar.LENGTH_LONG);
                }
                nombre.setText("");
                escuela.setText("");
                especialidad.setText("");
                numTel.setText("");
                nControl.setText("");

            }
        });

    }

}
