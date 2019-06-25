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
import com.studio.oscar.agendaescolar.Adapters.AdapterNotas;
import com.studio.oscar.agendaescolar.Add.AddNotas;
import com.studio.oscar.agendaescolar.Datos.Read;
import com.studio.oscar.agendaescolar.Edit.EditarNotas;
import com.studio.oscar.agendaescolar.Objetos.Nota;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class NotasFragment extends Fragment {

    ListView lista;
    String varAux;
    long posicionList;
    public ArrayList<Nota> arrayList;
    AdapterNotas adapter;
    final private String nameClass = "NotasFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.notas, group, false);
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.UpdateList(Read.ReadNotas());
    }

    @Override
    public void onActivityCreated(Bundle saved){
        super.onActivityCreated(saved);
        lista = getActivity().findViewById(R.id.listNotas);
        arrayList = Read.ReadNotas();
        adapter = new AdapterNotas(getActivity(), arrayList);
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
                Intent add = new Intent(getActivity(), AddNotas.class);
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

        if(v.getId() == R.id.listNotas){
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;
            varAux = lista.getAdapter().getItem(info.position).toString();
            posicionList = lista.getItemIdAtPosition(info.position);

            inflater.inflate(R.menu.menu_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editar:
                final int pos = (int)posicionList;
                Nota obj = (Nota)arrayList.get(pos);
                String text = obj.getText();

                Intent editar = new Intent(getActivity(), EditarNotas.class);
                editar.putExtra("text",text);
                editar.putExtra("posicion",pos+"");
                startActivity(editar);
                return true;
            case R.id.delete:
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9").listFiles();
                for (short i = 0; i <= arrayFile.length; i++){
                    if(posicionList == i){
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/9");
                        String getname = arrayFile[i].getName();
                        File fn = new File(path.getAbsolutePath(), getname);
                        fn.delete();
                        adapter.UpdateList(Read.ReadNotas());
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
