package com.studio.oscar.agendaescolar.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studio.oscar.agendaescolar.Objetos.Nota;
import com.studio.oscar.agendaescolar.R;

import java.util.ArrayList;


public class AdapterNotas extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Nota> items;

    public AdapterNotas () {

    }

    public AdapterNotas (Activity activity, ArrayList<Nota> items) {
        this.activity = activity;
        this.items = items;
    }

    public void UpdateList(ArrayList<Nota> newlist) {
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

    public void addAll(ArrayList<Nota> v) {
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
            v = inf.inflate(R.layout.adapter_notas, null);
        }

        Nota dir = items.get(position);

        TextView text = v.findViewById(R.id.text);
        text.setText(dir.getText());

        return v;
    }

}
