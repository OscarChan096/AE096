package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class Inf implements Serializable {

    private String user, password;

    public Inf(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
