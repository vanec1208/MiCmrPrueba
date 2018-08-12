package com.example.unica.pruebaandroidnapps.gui.Actividades;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Actividad;
import com.example.unica.pruebaandroidnapps.beans.Negocio;
import com.example.unica.pruebaandroidnapps.beans.Organizacion;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.transactions.ActividadTransactions;

import java.util.ArrayList;

public class ListActividadesDialogFragment extends DialogFragment {
    private ListView lvActividades;
    private ListActividadesItemAdapter itemAdapter;
    private ImageView imgClose;

    private Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_list_actividades, null);

        lvActividades = view.findViewById(R.id.lvActividades);
        imgClose = view.findViewById(R.id.imgClose);

        ArrayList<Actividad> listActividades = new ActividadTransactions(context).getListActividades();
        itemAdapter = new ListActividadesItemAdapter(context, listActividades);
        lvActividades.setAdapter(itemAdapter);

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }
}
