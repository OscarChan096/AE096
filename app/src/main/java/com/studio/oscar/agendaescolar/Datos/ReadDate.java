package com.studio.oscar.agendaescolar.Datos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReadDate {

    public static String getFecha(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

}
