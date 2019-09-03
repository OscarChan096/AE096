package com.studio.oscar.agendaescolar.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.studio.oscar.agendaescolar.Activities.NotasFragment;
import com.studio.oscar.agendaescolar.Activities.Settings;
import com.studio.oscar.agendaescolar.Activities.TareasFragment;
import com.studio.oscar.agendaescolar.Adapters.AdapterAgenda;
import com.studio.oscar.agendaescolar.Add.AddAsignaturas;
import com.studio.oscar.agendaescolar.Datos.ConversionObj;
import com.studio.oscar.agendaescolar.Datos.Read;
import com.studio.oscar.agendaescolar.Objetos.Agenda;
import com.studio.oscar.agendaescolar.R;
import com.studio.oscar.agendaescolar.ui.main.PageViewModel;

import java.util.ArrayList;

public class AgendaFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    BottomNavigationView bottomNavigationView;

    ListView lista;
    ArrayList<Agenda> arrayList;
    AdapterAgenda adapterAgenda;

    public static AgendaFragment newInstance(int index){
        AgendaFragment fragment = new AgendaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER,index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null){
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.agenda_fragment, group, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bottomNavigationView = (BottomNavigationView)getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        ConversionObj.ConverterToAgenda();
        lista = getActivity().findViewById(R.id.list_agenda_fragment);
        arrayList = Read.getListAgenda();
        adapterAgenda = new AdapterAgenda(getActivity(),arrayList);
        lista.setAdapter(adapterAgenda);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        //Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_horario:
                Intent tarea = new Intent(getActivity(), TareasFragment.class);
                startActivity(tarea);
                break;
            case R.id.navigation_tareas:
                Intent nota = new Intent(getActivity(), NotasFragment.class);
                startActivity(nota);
                break;
        }
        return true;
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

}
