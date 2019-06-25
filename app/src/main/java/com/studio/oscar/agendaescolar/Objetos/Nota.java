package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class Nota implements Serializable {

    private String text;

    public Nota(String text){
        this.text = text;
    }

    void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

}
