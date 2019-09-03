package com.studio.oscar.agendaescolar.Add;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

public class AddNotas extends AppCompatActivity implements View.OnClickListener{

    EditText boxTextNotas;
    View btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notas);

        boxTextNotas = findViewById(R.id.inTarea);
        btnSave = findViewById(R.id.btnguardar);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btnguardar:
                String notas = "notas";
                String obj = ".nt";
                String name = notas+obj;
                String text = boxTextNotas.getText().toString();
                try{
                    File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9");
                    File fileName;
                    if(path.isDirectory()){
                        File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9").listFiles();

                        int next = arrayFile.length;

                        name = notas+"("+next+")"+obj; //nfile
                        fileName = new File(path.getAbsolutePath(), name);
                        while(true){
                            if(fileName.exists()){
                                next++;
                                name = notas+"("+next+")"+obj;
                                fileName = new File(path.getAbsolutePath(), name);
                            }else{
                                break;
                            }

                        }

                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        Nota n = new Nota(text);
                        salida.writeObject(n);

                    }else{
                        path.mkdirs(); // crea la carpeta
                        File fileNameX = new File(path.getAbsolutePath(), name);
                        FileOutputStream fileOut = new FileOutputStream(fileNameX);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                        Nota n = new Nota(text);
                        salida.writeObject(n);

                        Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
                    }

                }catch(FileNotFoundException fnfe){
                    Toast.makeText(getApplicationContext(),"archivo no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
                }catch(IOException ioe){
                    Snackbar.make(view,"Error}: "+ioe.getMessage(), Snackbar.LENGTH_LONG);
                }
                boxTextNotas.setText("");
                break;
        }
    }

} // end class
