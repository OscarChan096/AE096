package com.studio.oscar.agendaescolar.CreatedFiles;

import com.studio.oscar.agendaescolar.Datos.ReadDate;
import com.studio.oscar.agendaescolar.Datos.Write;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

public class mkdirsDirectorios {

    public static void createdDirectory12x(){
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/12x");
        if(!path.isDirectory()) {
            path.mkdirs();
            Write.WriteInfoApp(ReadDate.getDia(), false);
        }
    }

}
