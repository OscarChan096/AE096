package com.studio.oscar.agendaescolar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.oscar.agendaescolar.Adapters.AdapterTareas;
import com.studio.oscar.agendaescolar.Add.AddTareas;
import com.studio.oscar.agendaescolar.Datos.ConversionObj;
import com.studio.oscar.agendaescolar.Datos.Read;
import com.studio.oscar.agendaescolar.Edit.EditTareas;
import com.studio.oscar.agendaescolar.Objetos.homework;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class TareasFragment extends AppCompatActivity {

    ListView lista;
    String varAux;
    long posicionList;
    ArrayList<homework> arrayList;
    AdapterTareas adapter;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.tareas);

        lista = findViewById(R.id.lisTareas);
        arrayList = Read.ReadTareas();
        adapter = new AdapterTareas(this, arrayList);
        lista.setAdapter(adapter);
        lista.setEmptyView(findViewById(R.id.emptyElement));

        registerForContextMenu(lista);
    }


    /***************** botones de la parte superior *******************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_add:
                Intent add = new Intent(this, AddTareas.class);
                startActivity(add);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /******************* MenuContextual *******************************/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.lisTareas){
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;

            varAux = lista.getAdapter().getItem(info.position).toString();
            posicionList = lista.getItemIdAtPosition(info.position);
            final int pos = (int) posicionList;
            homework obj = (homework) arrayList.get(pos);
            menu.setHeaderTitle(obj.getAsignatura().replaceAll("Asignatura: ", ""));

            inflater.inflate(R.menu.menu_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editar:
                final int pos = (int)posicionList;
                homework obj = arrayList.get(pos);
                String asign = obj.getAsignatura();
                String entrega = obj.getFechaEntrega();
                String tarea = obj.getTarea();

                Intent editar = new Intent(this,EditTareas.class);
                editar.putExtra("asign",asign.replaceAll("Asignatura: ",""));
                editar.putExtra("entrega",entrega.replaceAll("Fecha de entrega: ",""));
                editar.putExtra("tarea",tarea);
                editar.putExtra("posicion",pos+"");
                startActivity(editar);
                return true;
            case R.id.delete:
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8").listFiles();
                for (short i = 0; i <= arrayFile.length; i++){
                    if(posicionList == i){
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/8");
                        String getname = arrayFile[i].getName();
                        File fn = new File(path.getAbsolutePath(), getname);
                        fn.delete();
                        adapter.UpdateList(Read.ReadTareas());
                        ConversionObj.ConverterToAgenda();
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

}
