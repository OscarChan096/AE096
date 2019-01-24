package com.studio.oscar.agendaescolar.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studio.oscar.agendaescolar.Objetos.Evt;
import com.studio.oscar.agendaescolar.R;

import java.util.ArrayList;

public class AdapterEventos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Evt> items;

    public AdapterEventos () {}

    public AdapterEventos (Activity activity, ArrayList<Evt> items) {
        this.activity = activity;
        this.items = items;
    }

    public void UpdateList(ArrayList<Evt> newlist) {
        items = newlist;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Evt> v) {
        for (int i = 0; i < v.size(); i++) {
            items.add(v.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.adapter_eventos, null);
        }

        Evt dir = items.get(position);

        TextView nameEvt = v.findViewById(R.id.nameEvt);
        nameEvt.setText(dir.getNameEvt());

        TextView desc = v.findViewById(R.id.descripcion_evento_adapter);
        desc.setText(dir.getDescripcion());

        TextView fecha = v.findViewById(R.id.fecha_adapter_evt);
        fecha.setText(dir.getFecha());

        TextView hora = v.findViewById(R.id.hora_adapter_evt);
        hora.setText(dir.getHora());

        return v;
    }

}
