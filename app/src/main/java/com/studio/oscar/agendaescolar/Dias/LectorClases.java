package com.studio.oscar.agendaescolar.Dias;

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

import com.studio.oscar.agendaescolar.Adapters.AdapterDays;
import com.studio.oscar.agendaescolar.Add.AddToDay;
import com.studio.oscar.agendaescolar.Datos.ReadDay;
import com.studio.oscar.agendaescolar.Edit.EditarAsignaturas;
import com.studio.oscar.agendaescolar.Objetos.HClases;
import com.studio.oscar.agendaescolar.R;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class LectorClases extends AppCompatActivity {

    private ListView lista;
    private String varAux, dia, title;
    private long posicionList;
    private ArrayList arrayList;

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        Intent intent = getIntent();
        dia = intent.getStringExtra("dia");
        title = intent.getStringExtra("title");
        setTitle(title);
        setContentView(R.layout.lunes);

        arrayList = ReadDay.Read(Integer.parseInt(dia));
        lista = findViewById(R.id.listLunes);
        lista.setEmptyView(findViewById(R.id.emptyElement));
        //lista.setfChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        AdapterDays adapter = new AdapterDays(this, arrayList);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);
    }

    /***************** botones de la parte superior *******************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add:
                Intent id = new Intent(getApplicationContext(), AddToDay.class);
                id.putExtra("id", dia);
                switch (Integer.parseInt(dia)) {
                    case 0:
                        id.putExtra("title", "Lunes");
                        break;
                    case 1:
                        id.putExtra("title", "Martes");
                        break;
                    case 2:
                        id.putExtra("title", "Miercoles");
                        break;
                    case 3:
                        id.putExtra("title", "Jueves");
                        break;
                    case 4:
                        id.putExtra("title", "Viernes");
                        break;
                }
                startActivity(id);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /****************************************************************************/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if (v.getId() == R.id.listLunes) {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;

            varAux = lista.getAdapter().getItem(info.position).toString();
            posicionList = lista.getItemIdAtPosition(info.position);
            final int pos = (int) posicionList;
            HClases obj = (HClases) arrayList.get(pos);
            menu.setHeaderTitle(obj.getAsignatura().replaceAll("Asignatura: ", ""));

            inflater.inflate(R.menu.menu_context_days, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editar:
                final int pos = (int) posicionList;
                HClases obj = (HClases) arrayList.get(pos);
                String asign = obj.getAsignatura();
                String profesor = obj.getProfesor();
                String aula = obj.getAula();
                String hora = obj.getHora();
                String tohr = obj.getTohora();
                String name = obj.getNombreArchivo();
                int clavehr = obj.getId();

                Intent editar = new Intent(getApplicationContext(), EditarAsignaturas.class);
                editar.putExtra("asign", asign.replaceAll("Asignatura: ", ""));
                editar.putExtra("profesor", profesor.replaceAll("Docente: ", ""));
                editar.putExtra("aula", aula.replaceAll("Aula: ", ""));
                editar.putExtra("hora", hora.replaceAll("Hora: ", ""));
                editar.putExtra("tohr", tohr);
                editar.putExtra("nombrearchivo", name);
                editar.putExtra("clavehr",""+clavehr);
                editar.putExtra("id", dia);
                editar.putExtra("dia", dia);
                editar.putExtra("title", title);
                startActivity(editar);
                return true;
            case R.id.delete:
                final int posicion = (int) posicionList;
                HClases hc = (HClases) arrayList.get(posicion);
                String nombre = hc.getNombreArchivo();

                File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/" + dia);
                File fn = new File(path.getAbsolutePath(), nombre);
                fn.delete();
                Intent home_intent = new Intent(getApplicationContext(),
                        LectorClases.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home_intent.putExtra("dia", dia);
                home_intent.putExtra("title", title);
                startActivity(home_intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
