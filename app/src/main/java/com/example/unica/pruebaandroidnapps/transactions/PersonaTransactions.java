package com.example.unica.pruebaandroidnapps.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.db.PruebaTables;

import java.util.ArrayList;

public class PersonaTransactions extends InstanceDb {

    public PersonaTransactions(Context context) {
        super(context);
    }

    public void insertPersona(Persona persona){
        ContentValues values = new ContentValues();
        values.put(PruebaTables.PersonasTable.NOMBRE, persona.getNombre());
        values.put(PruebaTables.PersonasTable.TELEFONO, persona.getTelefono());
        values.put(PruebaTables.PersonasTable.EMAIL, persona.getEmail());

        db.insert(PruebaTables.PersonasTable.TABLE_NAME, null, values);
    }

    public ArrayList<Persona> getListPersonas(){
        ArrayList<Persona> listPersonas = new ArrayList<>();

        String[] col = {
                PruebaTables.PersonasTable.ID,
                PruebaTables.PersonasTable.NOMBRE,
                PruebaTables.PersonasTable.TELEFONO,
                PruebaTables.PersonasTable.EMAIL
        };

        Cursor c = db.query(PruebaTables.PersonasTable.TABLE_NAME, col, null, null, null,
                null, null);

        while (c.moveToNext()) {
            Persona persona = new Persona();
            persona.setId(c.getInt(0));
            persona.setNombre(c.getString(1));
            persona.setTelefono(c.getString(2));
            persona.setEmail(c.getString(3));

            listPersonas.add(persona);
        }

        return listPersonas;
    }

    public ArrayList<Persona> getListPersonasSpn(){
        ArrayList<Persona> listPersonas = new ArrayList<>();

        String[] col = {
                PruebaTables.PersonasTable.ID,
                PruebaTables.PersonasTable.NOMBRE,
        };

        Cursor c = db.query(PruebaTables.PersonasTable.TABLE_NAME, col, null, null, null,
                null, null);

        while (c.moveToNext()) {
            listPersonas.add(new Persona(c.getInt(0), c.getString(1)));
        }

        return listPersonas;
    }
}
