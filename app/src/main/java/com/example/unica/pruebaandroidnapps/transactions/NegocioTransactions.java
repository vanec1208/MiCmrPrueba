package com.example.unica.pruebaandroidnapps.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.unica.pruebaandroidnapps.beans.Negocio;
import com.example.unica.pruebaandroidnapps.beans.Organizacion;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.db.PruebaTables;

import java.util.ArrayList;

public class NegocioTransactions extends InstanceDb {
    public NegocioTransactions(Context context) {
        super(context);
    }

    public void insertNegocio(Negocio negocio){
        ContentValues values = new ContentValues();
        values.put(PruebaTables.NegociosTable.TITULO, negocio.getTitulo());
        values.put(PruebaTables.NegociosTable.DESCRIPCION, negocio.getDescripcion());
        if(negocio.getOrganizacion() != null) values.put(PruebaTables.NegociosTable.ORGANIZACION, negocio.getOrganizacion().getId());
        if(negocio.getPersona() != null) values.put(PruebaTables.NegociosTable.PERSONA, negocio.getPersona().getId());
        values.put(PruebaTables.NegociosTable.VALOR, negocio.getValor());
        values.put(PruebaTables.NegociosTable.FECHA_CIERRE, negocio.getFechaCierre());
        values.put(PruebaTables.NegociosTable.ESTADO, negocio.getEstado());

        db.insert(PruebaTables.NegociosTable.TABLE_NAME,  null, values);
    }

    public ArrayList<Negocio> getListNegocios(){
        ArrayList<Negocio> listNegocios = new ArrayList<>();

        String col =
                "N." + PruebaTables.NegociosTable.ID + ", " +                   //0
                "N." + PruebaTables.NegociosTable.TITULO + ", " +               //1
                "N." + PruebaTables.NegociosTable.DESCRIPCION + ", " +          //2
                "N." + PruebaTables.NegociosTable.ORGANIZACION + ", " +         //3
                "N." + PruebaTables.NegociosTable.PERSONA + ", " +              //4
                "N." + PruebaTables.NegociosTable.VALOR + ", " +                //5
                "N." + PruebaTables.NegociosTable.FECHA_CIERRE + ", " +         //6
                "N." + PruebaTables.NegociosTable.ESTADO + ", " +               //7
                "O." + PruebaTables.OrganizacionesTable.NOMBRE + ", " +         //8
                "P." + PruebaTables.PersonasTable.NOMBRE                       //9
                ;

        Cursor c = db.rawQuery("SELECT " + col + " FROM " + PruebaTables.NegociosTable.TABLE_NAME +
                " N LEFT JOIN " + PruebaTables.OrganizacionesTable.TABLE_NAME +
                " O ON N." + PruebaTables.NegociosTable.ORGANIZACION + " = O." + PruebaTables.OrganizacionesTable.ID +
                " LEFT JOIN " + PruebaTables.PersonasTable.TABLE_NAME +
                " P ON N." + PruebaTables.NegociosTable.PERSONA + " = P." + PruebaTables.PersonasTable.ID,
                null);

        while(c.moveToNext()){
            int idOrganizacion = c.getInt(3);
            int idPersona = c.getInt(4);

            Negocio negocio = new Negocio();
            negocio.setId(c.getInt(0));
            negocio.setTitulo(c.getString(1));
            negocio.setDescripcion(c.getString(2));
            negocio.setOrganizacion(idOrganizacion == 0 ? null : new Organizacion(idOrganizacion, c.getString(8)));
            negocio.setPersona(idPersona == 0 ? null : new Persona(idPersona, c.getString(9)));
            negocio.setValor(c.getString(5));
            negocio.setFechaCierre(c.getString(6));
            negocio.setEstado(c.getString(7));

            listNegocios.add(negocio);
        }

        return listNegocios;
    }

    public ArrayList<Negocio> getListNegociosSpn(){
        ArrayList<Negocio> listNegocios = new ArrayList<>();

        String [] col = {
                        PruebaTables.NegociosTable.ID + ", " +
                        PruebaTables.NegociosTable.TITULO
                    };

        Cursor c = db.query(PruebaTables.NegociosTable.TABLE_NAME, col,
                null, null, null, null, null);

        while(c.moveToNext()){
            listNegocios.add(new Negocio(c.getInt(0), c.getString(1)));
        }

        return listNegocios;
    }
}
