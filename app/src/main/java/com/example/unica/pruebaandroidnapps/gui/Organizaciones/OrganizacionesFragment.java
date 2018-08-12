package com.example.unica.pruebaandroidnapps.gui.Organizaciones;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.gui.Personas.CrearPersonaDialogFragment;

public class OrganizacionesFragment extends Fragment {
    private Button btnCrear;
    private Button btnListar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organizaciones, container, false);

        btnCrear = view.findViewById(R.id.btnCrear);
        btnListar = view.findViewById(R.id.btnListar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearOrganizacionDialogFragment crearOrganizacionFragment = new CrearOrganizacionDialogFragment();
                crearOrganizacionFragment.show(getFragmentManager(), "crearOrganizacion");
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListOrganizacionesDialogFragment listOrganizacionesDialog = new ListOrganizacionesDialogFragment();
                listOrganizacionesDialog.show(getFragmentManager(), "listOrganizaciones");
            }
        });

        return view;
    }
}
