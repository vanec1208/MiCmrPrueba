package com.example.unica.pruebaandroidnapps.gui.Negocios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Negocio;
import com.example.unica.pruebaandroidnapps.beans.Organizacion;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.transactions.NegocioTransactions;
import com.example.unica.pruebaandroidnapps.transactions.OrganizacionTransactions;
import com.example.unica.pruebaandroidnapps.transactions.PersonaTransactions;

import java.util.ArrayList;

public class CrearNegocioDialogFragment extends DialogFragment {
    private EditText txtTitulo;
    private EditText txtDescripcion;
    private Spinner spnOrganizacion;
    private Spinner spnPersona;
    private EditText txtValor;
    private EditText txtFechaCierre;
    private ImageView imgCalendar;
    private EditText txtEstado;
    private Button btnAgregar;
    private Button btnCancelar;

    private Context context;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_crear_negocio, null);

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        spnOrganizacion = view.findViewById(R.id.spnOrganizacion);
        spnPersona = view.findViewById(R.id.spnPersona);
        txtValor = view.findViewById(R.id.txtValor);
        txtFechaCierre = view.findViewById(R.id.txtFechaCierre);
        imgCalendar = view.findViewById(R.id.imgCalendar);
        txtEstado = view.findViewById(R.id.txtEstado);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnCancelar = view.findViewById(R.id.btnCancelar);

        ArrayList<Organizacion> listaO = new OrganizacionTransactions(context).getListOrganizacionesSpn();
        Organizacion organizacion = new Organizacion(0, "Seleccione Organización:");
        listaO.add(0, organizacion);

        ArrayAdapter<Organizacion> adapterO = new ArrayAdapter<>(context,
                R.layout.support_simple_spinner_dropdown_item, listaO);
        spnOrganizacion.setAdapter(adapterO);

        ArrayList<Persona> listaP = new PersonaTransactions(context).getListPersonasSpn();
        Persona Persona = new Persona(0, "Seleccione Persona:");
        listaP.add(0, Persona);

        ArrayAdapter<Persona> adapterP = new ArrayAdapter<>(context,
                R.layout.support_simple_spinner_dropdown_item, listaP);
        spnPersona.setAdapter(adapterP);

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogFragment datePickerDialog = new DatePickerDialogFragment();
                datePickerDialog.show(getChildFragmentManager(), "datePicker");
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strTitulo = txtTitulo.getText().toString();
                String strDescripcion = txtDescripcion.getText().toString();
                int idOrganizacion = ((Organizacion) spnOrganizacion.getSelectedItem()).getId();
                int idPersona = ((Persona) spnPersona.getSelectedItem()).getId();
                String strValor = txtValor.getText().toString();
                String strFechaCierre = txtFechaCierre.getText().toString();
                String strEstado = txtEstado.getText().toString();

                if(!strTitulo.equals("") || !strDescripcion.equals("") || idOrganizacion != 0 || idPersona != 0 ||
                        !strValor.equals("") || !strFechaCierre.equals("") || !strEstado.equals("")) {
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
                final String strTitulo = txtTitulo.getText().toString();
                final String strDescripcion = txtDescripcion.getText().toString();
                final int idOrganizacion = ((Organizacion) spnOrganizacion.getSelectedItem()).getId();
                final int idPersona = ((Persona) spnPersona.getSelectedItem()).getId();
                final String strValor = txtValor.getText().toString();
                final String strFechaCierre = txtFechaCierre.getText().toString();
                final String strEstado = txtEstado.getText().toString();

                //Validar que tenga todos los campos
                if(strTitulo.equals("") || strDescripcion.equals("") || strValor.equals("") || strFechaCierre.equals("") ||
                        strEstado.equals("")){
                    Toast.makeText(context, "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Crear Negocio en la BD
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("¿ESTÁ SEGURO(A) DE GUARDAR CAMBIOS?");
                alertDialogBuilder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int id) {
                        Negocio negocio = new Negocio();
                        negocio.setTitulo(strTitulo);
                        negocio.setDescripcion(strDescripcion);
                        negocio.setOrganizacion(idOrganizacion == 0 ? null : new Organizacion(idOrganizacion));
                        negocio.setPersona(idPersona == 0 ? null : new Persona(idPersona));
                        negocio.setValor(strValor);
                        negocio.setFechaCierre(strFechaCierre);
                        negocio.setEstado(strEstado);

                        new NegocioTransactions(context).insertNegocio(negocio);

                        Toast.makeText(context, "Negocio creado exitosamente", Toast.LENGTH_SHORT).show();
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

    public void setDate(String date){
        txtFechaCierre.setText(date);
    }

    public static class DatePickerDialogFragment extends DialogFragment {
        private DatePicker datePicker;
        private Button btnAceptar;
        private Button btnCancelar;

        private Context context;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_date, null);

            datePicker = view.findViewById(R.id.date);
            btnAceptar = view.findViewById(R.id.btnAceptar);
            btnCancelar = view.findViewById(R.id.btnCancelar);

            builder.setView(view);
            final AlertDialog dialog = builder.create();

            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });

            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String year = String.valueOf(datePicker.getYear());
                    String month = (datePicker.getMonth() + 1 < 10 ? "0" : "") + String.valueOf(datePicker.getMonth() + 1);
                    String day = (datePicker.getDayOfMonth() < 10 ? "0" : "") + String.valueOf(datePicker.getDayOfMonth());

                    String date = year + "-" + month + "-" + day;

                    ((CrearNegocioDialogFragment) getParentFragment()).setDate(date);

                    dialog.dismiss();
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
}
