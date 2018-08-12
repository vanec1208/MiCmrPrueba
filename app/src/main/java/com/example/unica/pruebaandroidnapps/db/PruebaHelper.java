package com.example.unica.pruebaandroidnapps.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PruebaHelper extends SQLiteOpenHelper {

    public PruebaHelper(Context context) {
        super(context, "pruebaNatApps.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PruebaTables.PersonasTable.CREATE);
        db.execSQL(PruebaTables.OrganizacionesTable.CREATE);
        db.execSQL(PruebaTables.NegociosTable.CREATE);
        db.execSQL(PruebaTables.ActividadesTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
