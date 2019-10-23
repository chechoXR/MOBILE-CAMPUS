package com.app.college.mobilecampus.session;

import com.app.college.mobilecampus.Model.Estudiante;

public class UserSession {

   private int id;
   private String user;
   private Boolean active;

    public UserSession(int id, String user, Boolean active) {
        this.id = id;
        this.user = user;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
