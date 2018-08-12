package com.example.unica.pruebaandroidnapps.transactions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.unica.pruebaandroidnapps.db.PruebaDb;

public class InstanceDb {

    protected Context context;
    protected SQLiteDatabase db;

    public InstanceDb(Context context) {
        this.context = context;
        db = PruebaDb.getInstance(context);
    }
}
