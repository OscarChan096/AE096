package com.studio.oscar.agendaescolar.Activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Datos.ReadInicio;
import com.studio.oscar.agendaescolar.Fragments.DialogoInicio;
import com.studio.oscar.agendaescolar.Objetos.Inf;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class Settings extends AppCompatActivity implements DialogoInicio.OnDialogoInicioListener{

    boolean bol;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.settings);

        final EditText user = findViewById(R.id.inUsuario);
        final EditText password = findViewById(R.id.inPassword);

        Button btn = findViewById(R.id.btnguardarS);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/00"); // obtiene el acceso a la memoria interna y obtiene el directorio
                File fileName = new File(path.getAbsolutePath(), "0.usp");
                bol = (!fileName.exists())?true:false;
                if(bol) {
                    saveInf(user.getText().toString(), password.getText().toString());
                }else{
                    Snackbar.make(findViewById(android.R.id.content), "Identifiquese", Snackbar.LENGTH_LONG)
                            .setAction("Iniciar Sesion", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    new DialogoInicio().show(fragmentManager, "Inicio");
                                }
                            })
                            .show();
                }
            }
        });

    }

    private void saveInf(String user, String password){
        String horario = "0";
        String ext = ".usp";
        String name = horario+ext;

        if((user.length() == 0 && password.length() == 0) || (user.length() == 0 || password.length() == 0)){
            Snackbar.make(findViewById(android.R.id.content),"Todos los campos son obligatorios",Snackbar.LENGTH_LONG).show();
        }else {
            try {
                File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/00");
                File fileName = new File(path.getAbsolutePath(), name);
                if (path.isDirectory() && fileName.exists()) {
                    name = horario + ext;
                    fileName = new File(path.getAbsolutePath(), name);
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                    Inf vi = new Inf(user, password);

                    salida.writeObject(vi);

                    Snackbar.make(findViewById(android.R.id.content), "Guardado", Snackbar.LENGTH_LONG).show();

                } else if (path.isDirectory()) {
                    File fileNameX = new File(path.getAbsolutePath(), name);
                    FileOutputStream fileOut = new FileOutputStream(fileNameX);
                    ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                    Inf vi = new Inf(user, password);

                    salida.writeObject(vi);

                    Snackbar.make(findViewById(android.R.id.content), "Guardado", Snackbar.LENGTH_LONG).show();
                }/* else {
                    path.mkdirs(); // crea la carpeta
                    File fileNameX = new File(path.getAbsolutePath(), name);
                    FileOutputStream fileOut = new FileOutputStream(fileNameX);
                    ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                    Inf vi = new Inf(user, password);

                    salida.writeObject(vi);

                    Snackbar.make(findViewById(android.R.id.content), "Guardado", Snackbar.LENGTH_LONG).show();
                }*/

            } catch (FileNotFoundException fnfe) {
                Toast.makeText(getApplicationContext(), "archivos no encontrado: " + fnfe.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException ioe) {
                Snackbar.make(findViewById(android.R.id.content), ioe.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void Verificar(String user, String password) {
        bol = ReadInicio.In(user,password);
    }
}
