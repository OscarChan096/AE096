package com.studio.oscar.agendaescolar.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studio.oscar.agendaescolar.Objetos.Usuario;
import com.studio.oscar.agendaescolar.R;

import java.util.ArrayList;

public class AdapterUsuario extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Usuario> items;

    public AdapterUsuario () {

    }

    public AdapterUsuario (Activity activity, ArrayList<Usuario> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Usuario> v) {
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
            v = inf.inflate(R.layout.adapter_usuario, null);
        }

        Usuario dir = items.get(position);

        TextView control = (TextView) v.findViewById(R.id.control_user);
        control.setText(dir.getnControl());

        TextView nombre = (TextView) v.findViewById(R.id.nombre_user);
        nombre.setText(dir.getNombre());

        TextView escuela = (TextView) v.findViewById(R.id.escuela_user);
        escuela.setText(dir.getEscuela());

        TextView especialidad = (TextView) v.findViewById(R.id.especialidad_user);
        especialidad.setText(dir.getEspecialidad());

        TextView numTel = (TextView) v.findViewById(R.id.numtel_user);
        numTel.setText(dir.getNumTel());

        return v;
    }

}
