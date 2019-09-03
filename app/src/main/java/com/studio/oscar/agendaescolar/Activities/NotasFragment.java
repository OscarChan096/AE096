package com.studio.oscar.agendaescolar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.oscar.agendaescolar.Adapters.AdapterNotas;
import com.studio.oscar.agendaescolar.Add.AddNotas;
import com.studio.oscar.agendaescolar.Datos.Read;
import com.studio.oscar.agendaescolar.Edit.EditarNotas;
import com.studio.oscar.agendaescolar.Objetos.Nota;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class NotasFragment extends AppCompatActivity {

    ListView lista;
    String varAux;
    long posicionList;
    public ArrayList<Nota> arrayList;
    AdapterNotas adapter;
    final private String nameClass = "NotasFragment";

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.notas);

        lista = findViewById(R.id.listNotas);
        arrayList = Read.ReadNotas();
        adapter = new AdapterNotas(this, arrayList);
        lista.setAdapter(adapter);
        lista.setEmptyView(findViewById(R.id.emptyElement));

        registerForContextMenu(lista);
    }

    @Override
    public void onResume(){
        super.onResume();
        //adapter.UpdateList(Read.ReadNotas());
    }

    /***************** botones de la parte superior *******************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_add:
                Intent add = new Intent(this, AddNotas.class);
                startActivity(add);
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

        MenuInflater inflater = getMenuInflater();

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

                Intent editar = new Intent(this, EditarNotas.class);
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
