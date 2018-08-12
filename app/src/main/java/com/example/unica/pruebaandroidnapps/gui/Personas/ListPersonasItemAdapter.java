package com.example.unica.pruebaandroidnapps.gui.Personas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Persona;

import java.util.ArrayList;

public class ListPersonasItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Persona> listPersonas;

    public class ViewHolder{
        TextView lblNombre;
        TextView lblTelefono;
        TextView lblEmail;
    }

    public ListPersonasItemAdapter(Context context, ArrayList<Persona> listPersonas) {
        this.context = context;
        this.listPersonas = listPersonas;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.adapter_list_personas, null);

            holder = new ViewHolder();
            holder.lblNombre = view.findViewById(R.id.lblNombre);
            holder.lblTelefono = view.findViewById(R.id.lblTelefono);
            holder.lblEmail = view.findViewById(R.id.lblEmail);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Persona persona = (Persona) getItem(i);

        holder.lblNombre.setText("Nombre: " + persona.getNombre());
        holder.lblTelefono.setText("Teléfono: " + persona.getTelefono());
        holder.lblEmail.setText("Email: " + persona.getEmail());

        return view;
    }

    @Override
    public int getCount() {
        return listPersonas.size();
    }

    @Override
    public Object getItem(int i) {
        return listPersonas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listPersonas.get(i).getId();
    }


}
