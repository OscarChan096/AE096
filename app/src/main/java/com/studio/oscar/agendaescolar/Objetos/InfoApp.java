package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class InfoApp implements Serializable {

    private String diaSistema;
    private boolean check;
    private boolean mkDirectory;

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

    public boolean isMkDirectory() {
        return mkDirectory;
    }

    public void setMkDirectory(boolean mkDirectory) {
        this.mkDirectory = mkDirectory;
    }
}
