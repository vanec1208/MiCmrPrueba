package com.example.unica.pruebaandroidnapps.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PruebaDb {

    private static SQLiteDatabase INSTANCE = null;

    private synchronized static void createInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PruebaHelper(context).getReadableDatabase();
        }
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (INSTANCE == null) createInstance(context);
        return INSTANCE;
    }
}
