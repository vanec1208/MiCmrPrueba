package com.example.unica.pruebaandroidnapps.gui.Personas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.transactions.PersonaTransactions;

import java.util.ArrayList;

public class ListPersonasDialogFragment extends DialogFragment {
    ListPersonasItemAdapter itemAdapter;
    private ListView lvPersonas;
    private ImageView imgClose;

    private Context context;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_list_personas, null);

        lvPersonas = view.findViewById(R.id.lvPersonas);
        imgClose = view.findViewById(R.id.imgClose);

        ArrayList<Persona> listPersonas = new PersonaTransactions(context).getListPersonas();
        itemAdapter = new ListPersonasItemAdapter(context, listPersonas);
        lvPersonas.setAdapter(itemAdapter);

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
