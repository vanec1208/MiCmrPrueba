package com.example.unica.pruebaandroidnapps.gui.Negocios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Negocio;

import java.util.ArrayList;

public class ListNegociosItemAdapter extends BaseAdapter {
    private ArrayList<Negocio> listNegocios;
    private Context context;

    public ListNegociosItemAdapter(Context context, ArrayList<Negocio> listNegocios) {
        this.context = context;
        this.listNegocios = listNegocios;
    }

    public class ViewHolder{
        TextView lblTitulo;
        TextView lblDescripcion;
        TextView lblOrganizacion;
        TextView lblPersona;
        TextView lblValor;
        TextView lblFechaCierre;
        TextView lblEstado;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.adapter_list_negocios, null);

            holder = new ViewHolder();

            holder.lblTitulo = view.findViewById(R.id.lblTitulo);
            holder.lblDescripcion = view.findViewById(R.id.lblDescripcion);
            holder.lblOrganizacion = view.findViewById(R.id.lblOrganizacion);
            holder.lblPersona = view.findViewById(R.id.lblPersona);
            holder.lblValor = view.findViewById(R.id.lblValor);
            holder.lblFechaCierre = view.findViewById(R.id.lblFechaCierre);
            holder.lblEstado = view.findViewById(R.id.lblEstado);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Negocio negocio = (Negocio) getItem(i);

        holder.lblTitulo.setText("Titulo: " + negocio.getTitulo());
        holder.lblDescripcion.setText("Descripción: " + negocio.getDescripcion());
        if(negocio.getOrganizacion() == null) holder.lblOrganizacion.setVisibility(View.GONE);
        else holder.lblOrganizacion.setText("Organización: " + negocio.getOrganizacion().getNombre());
        if(negocio.getPersona() == null) holder.lblPersona.setVisibility(View.GONE);
        else holder.lblPersona.setText("Persona: " + negocio.getPersona().getNombre());
        holder.lblValor.setText("Valor: " + negocio.getValor());
        holder.lblFechaCierre.setText("Fecha de cierre: " + negocio.getFechaCierre());
        holder.lblEstado.setText("Estado: " + negocio.getEstado());

        return view;
    }

    @Override
    public int getCount() {
        return listNegocios.size();
    }

    @Override
    public Object getItem(int i) {
        return listNegocios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listNegocios.get(i).getId();
    }
}
