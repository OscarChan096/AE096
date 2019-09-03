package com.studio.oscar.agendaescolar.Datos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReadDate {

    public static String getFecha(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

    public static String getDia(){
        String dia = "";
        Calendar calendar = Calendar.getInstance();
        //calendar.set();
        int diax = calendar.get(Calendar.DAY_OF_WEEK);
        switch (diax){
            case Calendar.MONDAY:
                dia = "lunes";
                break;
            case Calendar.TUESDAY:
                dia = "martes";
                break;
            case Calendar.WEDNESDAY:
                dia = "miercoles";
                break;
            case Calendar.THURSDAY:
                dia = "jueves";
                break;
            case Calendar.FRIDAY:
                dia = "viernes";
                break;
        }
        return dia;
    }

} // end class
