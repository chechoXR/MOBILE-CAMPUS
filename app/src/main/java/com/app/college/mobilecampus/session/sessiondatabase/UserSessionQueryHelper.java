package com.app.college.mobilecampus.session.sessiondatabase;

public class UserSessionQueryHelper {

    public static final String[] PROJECTION = {UserSessionEntry.ID,UserSessionEntry.NAME,UserSessionEntry.LASTNAME,UserSessionEntry.USER, UserSessionEntry.EMAIL,UserSessionEntry.ACTIVE};
    public static final String SELECTION_USER = UserSessionEntry.USER +" = ?";
}
