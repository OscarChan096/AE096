package com.studio.oscar.agendaescolar.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studio.oscar.agendaescolar.Objetos.Agenda;
import com.studio.oscar.agendaescolar.R;

import java.util.ArrayList;

public class AdapterAgenda extends BaseAdapter {

    Activity activity;
    ArrayList<Agenda> items;

    public AdapterAgenda(Activity activity, ArrayList<Agenda> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    public void clear(){
        items.clear();
    }

    public void addAll(ArrayList<Agenda> v){
        for (int i = 0; i < v.size(); i++)
            items.add(v.get(i));
    }

    @Override
    public Object getItem(int arg0){
        return items.get(arg0);
    }

    @Override
    public long getItemId(int posicion){
        return posicion;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent){
        View v = convertView;
        if (convertView == null){
            LayoutInflater inf = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.adapter_agenda, null);
        }
        Agenda dir = items.get(posicion);

        TextView name_activiy = v.findViewById(R.id.name_activity);
        name_activiy.setText(dir.getActivity_name());

        TextView fecha = v.findViewById(R.id.fecha);
        fecha.setText(dir.getFechaEntrega());

        TextView titulo = v.findViewById(R.id.titulo);
        titulo.setText(dir.getAsignatura());

        TextView descripcion = v.findViewById(R.id.descripcion);
        descripcion.setText((dir.getActivity_name().equals("Horario"))?dir.getAula():dir.getTarea());

        TextView hora = v.findViewById(R.id.hora);
        hora.setText((dir.getActivity_name().equals("Horario"))?dir.getHora()+" - "+dir.getTohora():"");

        return v;
    }

}
