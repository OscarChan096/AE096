package com.studio.oscar.agendaescolar.Adapters;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.oscar.agendaescolar.Objetos.tilesDias;
import com.studio.oscar.agendaescolar.R;

import java.util.ArrayList;

public class AdapterHorario extends BaseAdapter {

    protected Fragment fragment;
    protected ArrayList<tilesDias> items;

    public AdapterHorario(Fragment fragment, ArrayList<tilesDias> items){
        this.fragment = fragment;
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    public void clear(){
        items.clear();
    }

    public void addAll(ArrayList<tilesDias> td){
        for (int i = 0; i < td.size(); i++){
            items.add(td.get(i));
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
            LayoutInflater inf = (LayoutInflater)fragment.getLayoutInflater();
            v = inf.inflate(R.layout.adapter_hora, null);
        }

        tilesDias dir = items.get(posicion);

        TextView title = (TextView) v.findViewById(R.id.title);
        title.setText(dir.getDay());

        ImageView imagen = v.findViewById(R.id.imageView);
        imagen.setImageResource(dir.getImage());

        return  v;
    }

}
