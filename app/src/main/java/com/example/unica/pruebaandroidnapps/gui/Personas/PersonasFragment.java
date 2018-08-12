package com.example.unica.pruebaandroidnapps.gui.Personas;

import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unica.pruebaandroidnapps.R;

public class PersonasFragment extends Fragment {
    private Button btnCrear;
    private Button btnListar;

    public PersonasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personas, container, false);

        btnCrear = view.findViewById(R.id.btnCrear);
        btnListar = view.findViewById(R.id.btnListar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearPersonaDialogFragment crearPersonaFragment = new CrearPersonaDialogFragment();
                crearPersonaFragment.show(getFragmentManager(), "crearPersona");
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListPersonasDialogFragment listPersonasFragment = new ListPersonasDialogFragment();
                listPersonasFragment.show(getFragmentManager(), "listPersonas");
            }
        });

        return view;
    }
}
