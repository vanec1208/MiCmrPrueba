package com.example.unica.pruebaandroidnapps.gui.Actividades;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.gui.Organizaciones.ListOrganizacionesDialogFragment;

public class ActividadesFragment extends Fragment {
    private Button btnCrear;
    private Button btnListar;

    public ActividadesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actividades, container, false);

        btnCrear = view.findViewById(R.id.btnCrear);
        btnListar = view.findViewById(R.id.btnListar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearActividadDialogFragment crearActividadFragment = new CrearActividadDialogFragment();
                crearActividadFragment.show(getFragmentManager(), "crearActividad");
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListActividadesDialogFragment listActividadesDialog = new ListActividadesDialogFragment();
                listActividadesDialog.show(getFragmentManager(), "listActividades");
            }
        });

        return view;
    }
}
