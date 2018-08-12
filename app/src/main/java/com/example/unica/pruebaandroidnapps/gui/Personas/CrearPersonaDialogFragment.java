package com.example.unica.pruebaandroidnapps.gui.Personas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.transactions.PersonaTransactions;
import com.example.unica.pruebaandroidnapps.util.Validaciones;

public class CrearPersonaDialogFragment extends DialogFragment {
    private EditText txtNombre;
    private EditText txtTelefono;
    private EditText txtEmail;
    private Button btnAgregar;
    private Button btnCancelar;

    private Context context;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_crear_persona, null);

        txtNombre = view.findViewById(R.id.txtNombre);
        txtTelefono = view.findViewById(R.id.txtTelefono);
        txtEmail = view.findViewById(R.id.txtEmail);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnCancelar = view.findViewById(R.id.btnCancelar);

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtEmail.setTextColor(Validaciones.isCorreoValido(editable.toString()) ? Color.BLACK :
                        Color.rgb(223,91,95));
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNombre = txtNombre.getText().toString();
                String strTelefono = txtTelefono.getText().toString();
                String strEmail = txtEmail.getText().toString();

                if(!strNombre.equals("") || !strTelefono.equals("") || !strEmail.equals("")) {
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
                final String strTelefono = txtTelefono.getText().toString();
                final String strEmail = txtEmail.getText().toString();

                //Validar que tenga todos los campos
                if(strNombre.equals("") || strTelefono.equals("") || strEmail.equals("")){
                    Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Validar correo
                if(!Validaciones.isCorreoValido(strEmail)){
                    Toast.makeText(context, "Correo no válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Crear Persona en DB
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("¿ESTÁ SEGURO(A) DE GUARDAR CAMBIOS?");
                alertDialogBuilder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int id) {
                        Persona persona = new Persona();
                        persona.setNombre(strNombre);
                        persona.setTelefono(strTelefono);
                        persona.setEmail(strEmail);

                        new PersonaTransactions(context).insertPersona(persona);

                        Toast.makeText(context, "Persona creada exitosamente", Toast.LENGTH_SHORT).show();
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
