package com.app.college.mobilecampus.session.sessiondatabase;

import android.provider.BaseColumns;

public abstract class UserSessionEntry implements BaseColumns {

    public static final String TABLE_NAME = "USER_SESSION";
    public static final String ID  = "_id";
    public static final String USER = "user";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String ACTIVE = "active";
    public static final String CODIGO = "codigo";
}
