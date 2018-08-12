package com.example.unica.pruebaandroidnapps.gui.Organizaciones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Organizacion;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.transactions.OrganizacionTransactions;
import com.example.unica.pruebaandroidnapps.transactions.PersonaTransactions;

public class CrearOrganizacionDialogFragment extends DialogFragment {
    private EditText txtNombre;
    private EditText txtDireccion;
    private EditText txtTelefono;
    private Button btnAgregar;
    private Button btnCancelar;

    private Context context;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =  getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_crear_organizacion, null);

        txtNombre = view.findViewById(R.id.txtNombre);
        txtDireccion = view.findViewById(R.id.txtDireccion);
        txtTelefono = view.findViewById(R.id.txtTelefono);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnCancelar = view.findViewById(R.id.btnCancelar);

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNombre = txtNombre.getText().toString();
                String strDireccion = txtDireccion.getText().toString();
                String strTelefono = txtTelefono.getText().toString();

                if(!strNombre.equals("") || !strDireccion.equals("") || !strTelefono.equals("")) {
                    //Descartar cambios
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);
                    alertDialogBuilder.setMessage("¿DESCARTAR CAMBIOS?");
                    alertDialogBuilder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog1, int id) {
                            dialog.cancel();
                        }
                    }).setNegativeButton("NO", null);

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }else{
                    dialog.cancel();
                }
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strNombre = txtNombre.getText().toString();
                final String strDireccion = txtDireccion.getText().toString();
                final String strTelefono = txtTelefono.getText().toString();

                //Validar que tenga todos los campos
                if(strNombre.equals("") || strDireccion.equals("") || strTelefono.equals("")){
                    Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Crear organización en BD
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("¿ESTÁ SEGURO(A) DE GUARDAR CAMBIOS?");
                alertDialogBuilder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int id) {
                        Organizacion organizacion = new Organizacion();
                        organizacion.setNombre(strNombre);
                        organizacion.setDireccion(strDireccion);
                        organizacion.setTelefono(strTelefono);

                        new OrganizacionTransactions(context).insertOrganizacion(organizacion);

                        Toast.makeText(context, "Organización creada exitosamente", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).setNegativeButton("NO", null);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }
}
