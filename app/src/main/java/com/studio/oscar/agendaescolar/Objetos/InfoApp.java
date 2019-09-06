package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class InfoApp implements Serializable {

    private String diaSistema;
    private boolean check;

    public String getDiaSistema(){
        return diaSistema;
    }

    public void setDiaSistema(String diaSistema){
        this.diaSistema = diaSistema;
    }

    public boolean isCheck(){
        return check;
    }

    public void setCheck(boolean check){
        this.check = check;
    }

}
