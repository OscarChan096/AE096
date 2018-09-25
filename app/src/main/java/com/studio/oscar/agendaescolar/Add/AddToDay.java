package com.studio.oscar.agendaescolar.Add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.oscar.agendaescolar.Adapters.AdapterLectura;
import com.studio.oscar.agendaescolar.Datos.ReadDay;
import com.studio.oscar.agendaescolar.Edit.EditAsignDays;
import com.studio.oscar.agendaescolar.Edit.EditarAsignaturas;
import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class AddToDay extends AppCompatActivity {

    private String ID, title, varAux;
    private ListView lista;
    private long posicionList;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);

        Intent intent = getIntent();
        ID = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        setTitle("Agregar a "+title);
        setContentView(R.layout.add_to_day);

        // num 7 es la carpeta de asignaturas
        arrayList = ReadDay.Read(7);
        lista = (ListView)findViewById(R.id.list_add_asign);
        //lista.setEmptyView(findViewById(R.id.emptyElement));
        //lista.setfChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        AdapterLectura adapter = new AdapterLectura(this, arrayList);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                final int pos = posicion;
                HClases obj = (HClases)arrayList.get(pos);
                String asign = obj.getAsignatura();
                String profesor = obj.getProfesor();
                String aula = obj.getAula();

                Intent hr = new Intent(getApplicationContext(), AddHora.class);
                hr.putExtra("id",ID);
                hr.putExtra("asign", asign);
                hr.putExtra("profesor", profesor);
                hr.putExtra("aula", aula);
                hr.putExtra("title",title);
                startActivity(hr);

            }
        });
        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        if (v.getId() == R.id.list_add_asign){
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;
            varAux = lista.getAdapter().getItem(info.position).toString();
            posicionList = lista.getItemIdAtPosition(info.position);
            final int pos = (int)posicionList;
            HClases obj = (HClases)arrayList.get(pos);
            menu.setHeaderTitle(obj.getAsignatura().replace("Asignatura: ",""));
            inflater.inflate(R.menu.menu_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.editar:
            final int pos = (int)posicionList;
            HClases obj = (HClases)arrayList.get(pos);
            String asign = obj.getAsignatura();
            String profesor = obj.getProfesor();
            String aula = obj.getAula();

            Intent editar = new Intent(getApplicationContext(), EditAsignDays.class);
            editar.putExtra("asign",asign.replaceAll("Asignatura: ",""));
            editar.putExtra("profesor",profesor.replaceAll("Docente: ",""));
            editar.putExtra("aula",aula.replaceAll("Aula: ",""));
            editar.putExtra("posicion",pos+"");
            startActivity(editar);
            return true;
            case R.id.delete:
                File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/7").listFiles();
                for (short i = 0; i <= arrayFile.length; i++){
                    if(posicionList == i){
                        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.horario/7");
                        String getname = arrayFile[i].getName();
                        File fn = new File(path.getAbsolutePath(), getname);
                        fn.delete();
                        Intent home_intent = new Intent(getApplicationContext(),
                                AddToDay.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(home_intent);
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

} // end class
