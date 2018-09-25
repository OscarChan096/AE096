package com.studio.oscar.agendaescolar.Datos;

import com.studio.oscar.agendaescolar.Objetos.HClases;

import java.util.ArrayList;

public class Ordenamiento {

    public static void Horario(){
        ArrayList<HClases> list = Full();
        short[] hrArray = new short[list.size()];
        for(int i = 0; i < list.size(); ){

        }
    }

    public static ArrayList<HClases> Full(){
        ArrayList<HClases> list = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            HClases obj = (HClases) list.get(i);
            String strhr = obj.getHora();
            char decena = strhr.charAt(0);
            char unidad = strhr.charAt(1);
            String strD = String.valueOf(decena);
            String strU = String.valueOf(unidad);
        }
        return  list;
    }

}
