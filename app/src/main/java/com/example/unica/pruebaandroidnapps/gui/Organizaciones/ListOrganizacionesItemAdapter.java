package com.example.unica.pruebaandroidnapps.gui.Organizaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Organizacion;

import java.util.ArrayList;

public class ListOrganizacionesItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Organizacion> listOrganizaciones;

    public class ViewHolder{
        TextView lblNombre;
        TextView lblDireccion;
        TextView lblTelefono;
    }

    public ListOrganizacionesItemAdapter(Context context, ArrayList<Organizacion> listOrganizaciones) {
        this.context = context;
        this.listOrganizaciones = listOrganizaciones;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.adapter_list_organizaciones, null);

            holder = new ViewHolder();
            holder.lblNombre = view.findViewById(R.id.lblNombre);
            holder.lblDireccion = view.findViewById(R.id.lblDireccion);
            holder.lblTelefono = view.findViewById(R.id.lblTelefono);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Organizacion organizacion = (Organizacion) getItem(i);

        holder.lblNombre.setText("Nombre: " + organizacion.getNombre());
        holder.lblDireccion.setText("Dirección: " + organizacion.getDireccion());
        holder.lblTelefono.setText("Teléfono: " + organizacion.getTelefono());

        return view;
    }

    @Override
    public int getCount() {
        return listOrganizaciones.size();
    }

    @Override
    public Object getItem(int i) {
        return listOrganizaciones.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listOrganizaciones.get(i).getId();
    }
}
