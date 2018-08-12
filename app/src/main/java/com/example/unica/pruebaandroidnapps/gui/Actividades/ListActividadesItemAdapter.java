package com.example.unica.pruebaandroidnapps.gui.Actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Actividad;

import java.util.ArrayList;

public class ListActividadesItemAdapter extends BaseAdapter {
    private ArrayList<Actividad> listActividades;
    private Context context;

    public ListActividadesItemAdapter(Context context, ArrayList<Actividad> listNegocios) {
        this.context = context;
        this.listActividades = listNegocios;
    }

    public class ViewHolder{
        TextView lblDescripcion;
        TextView lblTipo;
        TextView lblOrganizacion;
        TextView lblPersona;
        TextView lblNegocio;
        TextView lblFecha;
        TextView lblHora;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.adapter_list_actividades, null);

            holder = new ViewHolder();

            holder.lblDescripcion = view.findViewById(R.id.lblDescripcion);
            holder.lblTipo = view.findViewById(R.id.lblTipo);
            holder.lblOrganizacion = view.findViewById(R.id.lblOrganizacion);
            holder.lblPersona = view.findViewById(R.id.lblPersona);
            holder.lblNegocio = view.findViewById(R.id.lblNegocio);
            holder.lblFecha = view.findViewById(R.id.lblFecha);
            holder.lblHora = view.findViewById(R.id.lblHora);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Actividad actividad = (Actividad) getItem(i);

        holder.lblDescripcion.setText("Descripción: " + actividad.getDescripcion());
        holder.lblTipo.setText("Tipo: " + actividad.getTipo());

        if(actividad.getOrganizacion() == null) {
            holder.lblOrganizacion.setVisibility(View.GONE);
        } else {
            holder.lblOrganizacion.setVisibility(View.VISIBLE);
            holder.lblOrganizacion.setText("Organización: " + actividad.getOrganizacion().getNombre());
        }

        if(actividad.getPersona() == null) {
            holder.lblPersona.setVisibility(View.GONE);
        }
        else {
            holder.lblPersona.setVisibility(View.VISIBLE);
            holder.lblPersona.setText("Persona: " + actividad.getPersona().getNombre());
        }

        if(actividad.getNegocio() == null) {
            holder.lblNegocio.setVisibility(View.GONE);
        }
        else {
            holder.lblNegocio.setVisibility(View.VISIBLE);
            holder.lblNegocio.setText("Negocio: " + actividad.getNegocio().getTitulo());
        }

        holder.lblFecha.setText("Fecha: " + actividad.getFecha());
        holder.lblHora.setText("Hora: " + actividad.getHora());

        return view;
    }

    @Override
    public int getCount() {
        return listActividades.size();
    }

    @Override
    public Object getItem(int i) {
        return listActividades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listActividades.get(i).getId();
    }
}
