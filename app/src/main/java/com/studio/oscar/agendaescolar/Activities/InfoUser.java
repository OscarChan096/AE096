package com.studio.oscar.agendaescolar.Activities;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.oscar.agendaescolar.Adapters.AdapterUsuario;
import com.studio.oscar.agendaescolar.Add.AddInfoUser;
import com.studio.oscar.agendaescolar.Datos.ReadInicio;
import com.studio.oscar.agendaescolar.Edit.EditInfoUser;
import com.studio.oscar.agendaescolar.Fragments.DialogoInicio;
import com.studio.oscar.agendaescolar.Interfaces.FileExists;
import com.studio.oscar.agendaescolar.Objetos.Usuario;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class InfoUser extends AppCompatActivity implements FileExists, DialogoInicio.OnDialogoInicioListener{

    ListView lista;
    String varAux;
    long posicionList;
    ArrayList<Usuario> arrayList;
    boolean bol;

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.info_user);
        lista = findViewById(R.id.listUser);

        /************************************************************************/
        String name;
        arrayList = new ArrayList<>();
        Usuario user;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if (path.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        Usuario aux = (Usuario) entrada.readObject();
                        String nombre = "Nombre: " + aux.getNombre();
                        String escuela = "Escuela: " + aux.getEscuela();
                        String especilidad = "Especialidad: " + aux.getEspecialidad();
                        String numTel = "Numero de telefono: " + aux.getNumTel();
                        String nControl = "Numero de control: " + aux.getnControl();

                        user = new Usuario(nombre, escuela, especilidad, numTel, nControl);
                        arrayList.add(user);

                    }
                    catch (FileNotFoundException ex) {}
                    catch (IOException e) {}
                    catch (ArrayIndexOutOfBoundsException e) {}
                    catch (ClassNotFoundException e) {}
                } // end for
            }
        }

        AdapterUsuario adapter = new AdapterUsuario(this, arrayList);
        lista.setAdapter(adapter);
        lista.setEmptyView(findViewById(R.id.emptyElement));
        /*************************************************************************/

        registerForContextMenu(lista);

    } // end onCreate

    /***************** botones de la parte superior *******************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add:
                if (!FileExist()) {
                    Intent id = new Intent(getApplicationContext(), AddInfoUser.class);
                    startActivity(id);
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Ya existe informacion de usuario", Snackbar.LENGTH_LONG)
                            .setAction("Editar", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(bol) {
                                        Usuario aux = (Usuario) arrayList.get(0);
                                        String nombre = aux.getNombre();
                                        String escuela = aux.getEscuela();
                                        String especilidad = aux.getEspecialidad();
                                        String numTel = aux.getNumTel();
                                        String nControl = aux.getnControl();

                                        Intent editar = new Intent(getApplicationContext(), EditInfoUser.class);
                                        editar.putExtra("nombre", nombre.replaceAll("Nombre: ", ""));
                                        editar.putExtra("escuela", escuela.replaceAll("Escuela: ", ""));
                                        editar.putExtra("especialidad", especilidad.replaceAll("Especialidad: ", ""));
                                        editar.putExtra("numtel", numTel.replaceAll("Numero de telefono: ", ""));
                                        editar.putExtra("control", nControl.replaceAll("Numero de control: ", ""));
                                        editar.putExtra("posicion", 0 + "");
                                        startActivity(editar);
                                    }else{
                                        Snackbar.make(findViewById(android.R.id.content),"Usuario y Contraseña incorrectos",Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /****************************************************************************/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if (v.getId() == R.id.listUser) {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;

            Usuario aux = arrayList.get(0);
            menu.setHeaderTitle(aux.getnControl().replaceAll("Numero de control: ", ""));
            varAux = lista.getAdapter().getItem(info.position).toString();
            posicionList = lista.getItemIdAtPosition(info.position);

            inflater.inflate(R.menu.menu_context_days, menu);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        new DialogoInicio().show(fragmentManager, "Inicio");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editar:

                if(bol){
                    Snackbar.make(findViewById(android.R.id.content),"verdadero",Snackbar.LENGTH_SHORT).show();
                    final int pos = (int) posicionList;
                    Usuario aux = arrayList.get(pos);
                    String nombre = aux.getNombre();
                    String escuela = aux.getEscuela();
                    String especilidad = aux.getEspecialidad();
                    String numTel = aux.getNumTel();
                    String nControl = aux.getnControl();

                    Intent editar = new Intent(getApplicationContext(), EditInfoUser.class);
                    editar.putExtra("nombre", nombre.replaceAll("Nombre: ", ""));
                    editar.putExtra("escuela", escuela.replaceAll("Escuela: ", ""));
                    editar.putExtra("especialidad", especilidad.replaceAll("Especialidad: ", ""));
                    editar.putExtra("numtel", numTel.replaceAll("Numero de telefono: ", ""));
                    editar.putExtra("control", nControl.replaceAll("Numero de control: ", ""));
                    editar.putExtra("posicion", pos + "");
                    startActivity(editar);
                }else{
                    Snackbar.make(findViewById(android.R.id.content),"Usuario y Contraseña incorrectos",Snackbar.LENGTH_SHORT).show();
                }

                return true;
            case R.id.delete:
                if(bol) {
                    File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf").listFiles();
                    for (short i = 0; i <= arrayFile.length; i++) {
                        if (posicionList == i) {
                            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf");
                            String getname = arrayFile[i].getName();
                            File fn = new File(path.getAbsolutePath(), getname);
                            fn.delete();
                            Intent home_intent = new Intent(getApplicationContext(),
                                    InfoUser.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(home_intent);
                            break;
                        } else {
                            continue;
                        }
                    }
                }else{
                    Snackbar.make(findViewById(android.R.id.content),"Usuario y Contraseña incorrectos",Snackbar.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean FileExist() {
        String name = "infoUser.inf";
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/inf");
        File fileName = new File(path.getAbsolutePath(), name);
        if (fileName.exists()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void Verificar(String user, String password) {
        bol = ReadInicio.In(user,password);
    }
}