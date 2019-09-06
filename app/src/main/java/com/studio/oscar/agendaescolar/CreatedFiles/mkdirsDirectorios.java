package com.studio.oscar.agendaescolar.CreatedFiles;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

public class mkdirsDirectorios {

    public static void createdDirectory12x(){
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.oscar.agendaescolar/12x");
        path.mkdirs();
    }

}
