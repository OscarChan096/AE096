package com.studio.oscar.agendaescolar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.oscar.agendaescolar.Activities.Settings;
import com.studio.oscar.agendaescolar.Adapters.AdapterTareas;
import com.studio.oscar.agendaescolar.Add.AddTareas;
import com.studio.oscar.agendaescolar.Datos.Read;
import com.studio.oscar.agendaescolar.Edit.EditTareas;
import com.studio.oscar.agendaescolar.Objetos.homework;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class TareasFragment extends Fragment {

    ListView lista;
    String varAux;
    long posicionList;
    ArrayList<homework> arrayList;
    AdapterTareas adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        //para agregar el boton en la parte del toolbar
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.tareas, group, false);
    }

    @Override
    public void onActivityCreated(Bundle saved){
        super.onActivityCreated(saved);
        lista = getActivity().findViewById(R.id.lisTareas);
        arrayList = Read.ReadTareas();
        adapter = new AdapterTareas(getActivity(), arrayList);
        lista.setAdapter(adapter);
        lista.setEmptyView(getActivity().findViewById(R.id.emptyElement));

        registerForContextMenu(lista);

    }



    /***************** botones de la parte superior *******************************/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menubar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_add:
                Intent add = new Intent(getActivity(), AddTareas.class);
                startActivity(add);
                return true;
            case R.id.usuario:
                Intent InfoUser = new Intent(getActivity(), com.studio.oscar.agendaescolar.Activities.InfoUser.class);
                startActivity(InfoUser);
                return true;
            case R.id.settings:
                Intent config = new Intent(getActivity(), Settings.class);
                startActivity(config);
                return true;
            case R.id.about:
                Intent About = new Intent(getActivity(), com.studio.oscar.agendaescolar.Activities.About.class);
                startActivity(About);
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

        MenuInflater inflater = getActivity().getMenuInflater();

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

                Intent editar = new Intent(getActivity(),EditTareas.class);
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
