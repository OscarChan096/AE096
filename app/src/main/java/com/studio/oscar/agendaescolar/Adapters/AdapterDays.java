package com.studio.oscar.agendaescolar.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.util.ArrayList;

public class AdapterDays extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<HClases> items;

    public AdapterDays(){}

    public AdapterDays(Activity activity, ArrayList<HClases> items){
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

    public void addAll(ArrayList<HClases> v){
        for (int i = 0; i < v.size(); i++){
            items.add(v.get(i));
        }
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
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.adapter_days, null);
        }

        HClases dir = items.get(posicion);

        TextView asign = (TextView) v.findViewById(R.id.list_asign);
        asign.setText(dir.getAsignatura());

        TextView profesor = (TextView) v.findViewById(R.id.list_profesor);
        profesor.setText(dir.getProfesor());

        TextView aula = (TextView) v.findViewById(R.id.list_aula);
        aula.setText(dir.getAula());

        TextView hora = (TextView) v.findViewById(R.id.list_hora);
        hora.setText(dir.getHora());

        TextView hasta = (TextView) v.findViewById(R.id.list_hasta);
        hasta.setText("a");

        TextView tohora = (TextView) v.findViewById(R.id.list_tohora);
        tohora.setText(dir.getTohora());

        return v;
    }

}
