package com.example.unica.pruebaandroidnapps.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.unica.pruebaandroidnapps.beans.Actividad;
import com.example.unica.pruebaandroidnapps.beans.Negocio;
import com.example.unica.pruebaandroidnapps.beans.Organizacion;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.db.PruebaTables;

import java.util.ArrayList;

public class ActividadTransactions extends InstanceDb {

    public ActividadTransactions(Context context) {
        super(context);
    }

    public void insertActividad(Actividad actividad){
        ContentValues values = new ContentValues();
        values.put(PruebaTables.ActividadesTable.DESCRIPCION, actividad.getDescripcion());
        values.put(PruebaTables.ActividadesTable.TIPO, actividad.getTipo());
        if(actividad.getOrganizacion() != null) values.put(PruebaTables.ActividadesTable.ORGANIZACION, actividad.getOrganizacion().getId());
        if(actividad.getPersona() != null) values.put(PruebaTables.ActividadesTable.PERSONA, actividad.getPersona().getId());
        if(actividad.getNegocio() != null) values.put(PruebaTables.ActividadesTable.NEGOCIO, actividad.getNegocio().getId());
        values.put(PruebaTables.ActividadesTable.FECHA, actividad.getFecha());
        values.put(PruebaTables.ActividadesTable.HORA, actividad.getHora());

        db.insert(PruebaTables.ActividadesTable.TABLE_NAME,  null, values);
    }

    public ArrayList<Actividad> getListActividades() {
        ArrayList<Actividad> listActividades = new ArrayList<>();

        String col =
                "A." + PruebaTables.ActividadesTable.ID + ", " +                    //0
                        "A." + PruebaTables.ActividadesTable.DESCRIPCION + ", " +           //1
                        "A." + PruebaTables.ActividadesTable.TIPO + ", " +                  //2
                        "A." + PruebaTables.ActividadesTable.ORGANIZACION + ", " +          //3
                        "A." + PruebaTables.ActividadesTable.PERSONA + ", " +               //4
                        "A." + PruebaTables.ActividadesTable.NEGOCIO + ", " +               //5
                        "A." + PruebaTables.ActividadesTable.FECHA + ", " +                 //6
                        "A." + PruebaTables.ActividadesTable.HORA + ", " +                  //7
                        "O." + PruebaTables.OrganizacionesTable.NOMBRE + ", " +             //8
                        "P." + PruebaTables.PersonasTable.NOMBRE + ", " +                   //9
                        "N." + PruebaTables.NegociosTable.TITULO                            //10
                ;

        Cursor c = db.rawQuery("SELECT " + col + " FROM " + PruebaTables.ActividadesTable.TABLE_NAME +
                        " A LEFT JOIN " + PruebaTables.OrganizacionesTable.TABLE_NAME +
                        " O ON A." + PruebaTables.ActividadesTable.ORGANIZACION + " = O." + PruebaTables.OrganizacionesTable.ID +
                        " LEFT JOIN " + PruebaTables.PersonasTable.TABLE_NAME +
                        " P ON A." + PruebaTables.ActividadesTable.PERSONA + " = P." + PruebaTables.PersonasTable.ID +
                        " LEFT JOIN " + PruebaTables.NegociosTable.TABLE_NAME +
                        " N ON A." + PruebaTables.ActividadesTable.NEGOCIO + " = N." + PruebaTables.NegociosTable.ID,
                null);

        while (c.moveToNext()) {
            int idOrganizacion = c.getInt(3);
            int idPersona = c.getInt(4);
            int idNegocio = c.getInt(5);

            Actividad actividad = new Actividad();
            actividad.setId(c.getInt(0));
            actividad.setDescripcion(c.getString(1));
            actividad.setTipo(c.getString(2));
            actividad.setOrganizacion(idOrganizacion == 0 ? null : new Organizacion(idOrganizacion, c.getString(8)));
            actividad.setPersona(idPersona == 0 ? null : new Persona(idPersona, c.getString(9)));
            actividad.setNegocio(idNegocio == 0 ? null : new Negocio(idNegocio, c.getString(10)));
            actividad.setFecha(c.getString(6));
            actividad.setHora(c.getString(7));

            listActividades.add(actividad);
        }

        return listActividades;
    }
}
