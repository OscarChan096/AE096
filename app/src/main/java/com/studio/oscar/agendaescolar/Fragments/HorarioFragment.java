package com.studio.oscar.agendaescolar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.studio.oscar.agendaescolar.Adapters.AdapterHorario;
import com.studio.oscar.agendaescolar.Add.AddAsignaturas;
import com.studio.oscar.agendaescolar.Dias.LectorClases;
import com.studio.oscar.agendaescolar.Objetos.tilesDias;
import com.studio.oscar.agendaescolar.R;

import java.util.ArrayList;

public class HorarioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.horario, group, false);
    }

    @Override
    public void onActivityCreated(Bundle saved){
        super.onActivityCreated(saved);
        tilesDias lun = new tilesDias("Lunes",R.drawable.ic);
        tilesDias mar = new tilesDias("Martes", R.drawable.ic);
        tilesDias mie = new tilesDias("Miercoles", R.drawable.ic);
        tilesDias jue = new tilesDias("Jueves", R.drawable.ic);
        tilesDias vie = new tilesDias("Viernes", R.drawable.ic);

        ArrayList<tilesDias> listDias = new ArrayList<>();
        listDias.add(lun);
        listDias.add(mar);
        listDias.add(mie);
        listDias.add(jue);
        listDias.add(vie);

        ListView lv = getActivity().findViewById(R.id.listDaysH);
        AdapterHorario adapterHorario = new AdapterHorario(this, listDias);
        lv.setAdapter(adapterHorario);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                final int pos = posicion;
                Intent activityIn = new Intent(getActivity(), LectorClases.class);
                activityIn.putExtra("dia",""+pos);
                switch (pos){
                    case 0:
                        activityIn.putExtra("title","Lunes");
                        break;
                    case 1:
                        activityIn.putExtra("title","Martes");
                        break;
                    case 2:
                        activityIn.putExtra("title","Miercoles");
                        break;
                    case 3:
                        activityIn.putExtra("title","Jueves");
                        break;
                    case 4:
                        activityIn.putExtra("title","Viernes");
                        break;
                }
                startActivity(activityIn);
            }
        });

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
                Intent addAsign = new Intent(getActivity(), AddAsignaturas.class);
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
