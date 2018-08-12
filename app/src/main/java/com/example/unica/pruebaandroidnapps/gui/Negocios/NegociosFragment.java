package com.example.unica.pruebaandroidnapps.gui.Negocios;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unica.pruebaandroidnapps.R;

public class NegociosFragment extends Fragment {
    private Button btnCrear;
    private Button btnListar;

    public NegociosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_negocios, container, false);

        btnCrear = view.findViewById(R.id.btnCrear);
        btnListar = view.findViewById(R.id.btnListar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearNegocioDialogFragment crearNegocioFragment = new CrearNegocioDialogFragment();
                crearNegocioFragment.show(getFragmentManager(), "crearNegocio");
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListNegociosDialogFragment listNegociosDialog = new ListNegociosDialogFragment();
                listNegociosDialog.show(getFragmentManager(), "listarNegocios");
            }
        });

        return view;
    }
}
