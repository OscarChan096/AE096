package com.studio.oscar.agendaescolar.Add;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.studio.oscar.agendaescolar.Adapters.AdapterLectura;
import com.studio.oscar.agendaescolar.Datos.ReadDay;
import com.studio.oscar.agendaescolar.Edit.EditAsignDays;
import com.studio.oscar.agendaescolar.Excepciones.IOExcepcionHora;
import com.studio.oscar.agendaescolar.Fragments.AddHorarioFragment;
import com.studio.oscar.agendaescolar.Fragments.DialogoInicio;
import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class AddToDay extends AppCompatActivity implements AddHorarioFragment.OnAddHorarioListener{

    private String ID, title, varAux;
    private ListView lista;
    private long posicionList;
    private String asign, profesor, aula;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);

        Intent intent = getIntent();
        ID = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        setTitle("Agregar a "+title);
        setContentView(R.layout.add_to_day);

        // num 7 es la carpeta de asignaturas
        arrayList = ReadDay.Read(7);
        lista = (ListView)findViewById(R.id.list_add_asign);
        //lista.setEmptyView(findViewById(R.id.emptyElement));
        //lista.setfChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        AdapterLectura adapter = new AdapterLectura(this, arrayList);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                final int pos = posicion;
                HClases obj = (HClases)arrayList.get(pos);
                asign = obj.getAsignatura();
                profesor = obj.getProfesor();
                aula = obj.getAula();

                FragmentManager fragmentManager = getSupportFragmentManager();
                new AddHorarioFragment().show(fragmentManager, "Agregar");

            }
        });
        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        if (v.getId() == R.id.list_add_asign){
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;
            varAux = lista.getAdapter().getItem(info.position).toString();
            posicionList = lista.getItemIdAtPosition(info.position);
            final int pos = (int)posicionList;
            HClases obj = (HClases)arrayList.get(pos);
            menu.setHeaderTitle(obj.getAsignatura().replace("Asignatura: ",""));
            inflater.inflate(R.menu.menu_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.editar:
            final int pos = (int)posicionList;
            HClases obj = (HClases)arrayList.get(pos);
            String asign = obj.getAsignatura();
            String profesor = obj.getProfesor();
            String aula = obj.getAula();

            Intent editar = new Intent(getApplicationContext(), EditAsignDays.class);
            editar.putExtra("asign",asign.replaceAll("Asignatura: ",""));
            editar.putExtra("profesor",profesor.replaceAll("Docente: ",""));
            editar.putExtra("aula",aula.replaceAll("Aula: ",""));
            editar.putExtra("posicion",pos+"");
            startActivity(editar);
            return true;
            case R.id.delete:
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7").listFiles();
                for (short i = 0; i <= arrayFile.length; i++){
                    if(posicionList == i){
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/7");
                        String getname = arrayFile[i].getName();
                        File fn = new File(path.getAbsolutePath(), getname);
                        fn.delete();
                        Intent home_intent = new Intent(getApplicationContext(),
                                AddToDay.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(home_intent);
                        break;
                    }else{
                        continue;
                    }
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void Agregar(String hr, String tohr){
        try {
            int clavehr;
            if (VerificarHora(hr, tohr)) {
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
                //Snackbar.make(findViewById(android.R.id.content), "id: " + clavehr, Snackbar.LENGTH_LONG).show();
                switch (Integer.parseInt(ID)) {
                    case 0:
                        lunes(clavehr, asign, profesor, aula, hr, tohr);
                        break;
                    case 1:
                        martes(clavehr, asign, profesor, aula, hr, tohr);
                        break;
                    case 2:
                        miercoles(clavehr, asign, profesor, aula, hr, tohr);
                        break;
                    case 3:
                        jueves(clavehr, asign, profesor, aula, hr, tohr);
                        break;
                    case 4:
                        viernes(clavehr, asign, profesor, aula, hr, tohr);
                        break;
                }
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Hora incorrecta", Snackbar.LENGTH_LONG).show();
            }
        }catch(IOExcepcionHora ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean VerificarHora(String hr, String toHr){
        if((hr.length() > 3 && hr.length() < 6) && (toHr.length() > 3 && toHr.length() < 6)){
            return true;
        }else{
            return false;
        }
    }

    //----------------------------------- DIAS ---------------------------------------

    void lunes(int clavehr, String a, String p, String au, String hora, String tohr){
        String horario = "lunes";
        String ext = ".hr";
        String name = horario+ext;

        try{
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/0");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/0").listFiles();

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
            }/*else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);

                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }*/

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
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/1");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/1").listFiles();

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
            }/*else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }*/

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
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/2");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/2").listFiles();

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
            }/*else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }*/

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
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/3");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/3").listFiles();

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
            }/*else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }*/

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
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/4");
            File fileName = new File(path.getAbsolutePath(), name);
            if(path.isDirectory() && fileName.exists()){
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/4").listFiles();

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
            }/*else{
                path.mkdirs(); // crea la carpeta
                File fileNameX = new File(path.getAbsolutePath(), name);
                FileOutputStream fileOut = new FileOutputStream(fileNameX);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                HClases vi = new HClases(clavehr,a,p,au,hora,tohr);
                vi.setNombreArchivo(name);
                salida.writeObject(vi);

                Toast.makeText(getApplicationContext(),"Agregado con exito",Toast.LENGTH_SHORT).show();
            }*/

        }catch(FileNotFoundException fnfe){
            Toast.makeText(getApplicationContext(),"archivos no encontrado: "+fnfe.getMessage(),Toast.LENGTH_SHORT).show();
        }catch(IOException ioe){
            Snackbar.make(findViewById(android.R.id.content), ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

} // end class
