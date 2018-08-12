package com.example.unica.pruebaandroidnapps.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.unica.pruebaandroidnapps.beans.Organizacion;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.db.PruebaTables;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

public class OrganizacionTransactions extends InstanceDb {
    public OrganizacionTransactions(Context context) {
        super(context);
    }

    public void insertOrganizacion(Organizacion organizacion){
        ContentValues values = new ContentValues();
        values.put(PruebaTables.OrganizacionesTable.NOMBRE, organizacion.getNombre());
        values.put(PruebaTables.OrganizacionesTable.DIRECCION, organizacion.getDireccion());
        values.put(PruebaTables.OrganizacionesTable.TELEFONO, organizacion.getTelefono());

        db.insert(PruebaTables.OrganizacionesTable.TABLE_NAME,  null, values);
    }

    public ArrayList<Organizacion> getListOrganizaciones(){
        ArrayList<Organizacion> listOrganizaciones = new ArrayList<>();

        String [] col = {
                PruebaTables.OrganizacionesTable.ID,
                PruebaTables.OrganizacionesTable.NOMBRE,
                PruebaTables.OrganizacionesTable.DIRECCION,
                PruebaTables.OrganizacionesTable.TELEFONO
        };

        Cursor c = db.query(PruebaTables.OrganizacionesTable.TABLE_NAME, col, null, null, null,
                null, null);

        while(c.moveToNext()){
            Organizacion organizacion = new Organizacion();
            organizacion.setId(c.getInt(0));
            organizacion.setNombre(c.getString(1));
            organizacion.setDireccion(c.getString(2));
            organizacion.setTelefono(c.getString(3));

            listOrganizaciones.add(organizacion);
        }

        return listOrganizaciones;
    }

    public ArrayList<Organizacion> getListOrganizacionesSpn(){
        ArrayList<Organizacion> listOrganizaciones = new ArrayList<>();

        String [] col = {
                PruebaTables.OrganizacionesTable.ID,
                PruebaTables.OrganizacionesTable.NOMBRE,
        };

        Cursor c = db.query(PruebaTables.OrganizacionesTable.TABLE_NAME, col, null, null, null,
                null, null);

        while(c.moveToNext()){
            listOrganizaciones.add(new Organizacion(c.getInt(0), c.getString(1)));
        }

        return listOrganizaciones;
    }
}
