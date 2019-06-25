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

import com.studio.oscar.agendaescolar.Activities.About;
import com.studio.oscar.agendaescolar.Activities.InfoUser;
import com.studio.oscar.agendaescolar.Activities.Settings;
import com.studio.oscar.agendaescolar.Adapters.AdapterEventos;
import com.studio.oscar.agendaescolar.Add.AddEventos;
import com.studio.oscar.agendaescolar.Datos.Read;
import com.studio.oscar.agendaescolar.Edit.EditarEventos;
import com.studio.oscar.agendaescolar.Objetos.Evt;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class EventosFragment extends Fragment {

    ListView lista;
    String varAux;
    long posicionList;
    ArrayList<Evt> arrayList;
    AdapterEventos adapter;

    public EventosFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.eventos, group, false);
    }

    public void onActivityCreated(Bundle saved){
        super.onActivityCreated(saved);
        lista = getActivity().findViewById(R.id.listEventos);
        arrayList = Read.ReadEventos();
        adapter = new AdapterEventos(getActivity(), arrayList);
        lista.setAdapter(adapter);
        lista.setEmptyView(getActivity().findViewById(R.id.emptyElement));

        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();

        if(v.getId() == R.id.listEventos){
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;

            varAux = lista.getAdapter().getItem(info.position).toString();
            posicionList = lista.getItemIdAtPosition(info.position);
            final int pos = (int) posicionList;
            Evt obj = arrayList.get(pos);
            menu.setHeaderTitle(obj.getNameEvt().replaceAll("Nombre del evento: ", ""));

            inflater.inflate(R.menu.menu_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editar:
                final int pos = (int)posicionList;
                Evt obj = arrayList.get(pos);
                String nameEvt = obj.getNameEvt();
                String desc = obj.getDescripcion();
                String fecha = obj.getFecha();
                String hora = obj.getHora();

                Intent editar = new Intent(getActivity(),EditarEventos.class);
                editar.putExtra("evt",nameEvt.replaceAll("Nombre del evento: ",""));
                editar.putExtra("des",desc);
                editar.putExtra("fecha",fecha.replaceAll("Fecha: ",""));
                editar.putExtra("hora",hora.replaceAll("Hora: ",""));
                editar.putExtra("posicion",pos+"");
                startActivity(editar);
                return true;
            case R.id.delete:
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/10").listFiles();
                for (short i = 0; i <= arrayFile.length; i++){
                    if(posicionList == i){
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/10");
                        String getname = arrayFile[i].getName();
                        File fn = new File(path.getAbsolutePath(), getname);
                        fn.delete();
                        adapter.UpdateList(Read.ReadEventos());
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menubar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_add:
                Intent addAsign = new Intent(getActivity(), AddEventos.class);
                startActivity(addAsign);
                return true;
            case R.id.usuario:
                Intent InfoUser = new Intent(getActivity(), InfoUser.class);
                startActivity(InfoUser);
                return true;
            case R.id.settings:
                Intent config = new Intent(getActivity(), Settings.class);
                startActivity(config);
                return true;
            case R.id.about:
                Intent About = new Intent(getActivity(), About.class);
                startActivity(About);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
