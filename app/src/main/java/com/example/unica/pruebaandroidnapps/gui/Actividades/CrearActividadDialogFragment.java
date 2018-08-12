package com.example.unica.pruebaandroidnapps.gui.Actividades;

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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.unica.pruebaandroidnapps.R;
import com.example.unica.pruebaandroidnapps.beans.Actividad;
import com.example.unica.pruebaandroidnapps.beans.Negocio;
import com.example.unica.pruebaandroidnapps.beans.Organizacion;
import com.example.unica.pruebaandroidnapps.beans.Persona;
import com.example.unica.pruebaandroidnapps.gui.Negocios.CrearNegocioDialogFragment;
import com.example.unica.pruebaandroidnapps.transactions.ActividadTransactions;
import com.example.unica.pruebaandroidnapps.transactions.NegocioTransactions;
import com.example.unica.pruebaandroidnapps.transactions.OrganizacionTransactions;
import com.example.unica.pruebaandroidnapps.transactions.PersonaTransactions;

import java.util.ArrayList;

public class CrearActividadDialogFragment extends DialogFragment {
    private EditText txtDescripcion;
    private EditText txtTipo;
    private Spinner spnOrganizacion;
    private Spinner spnPersona;
    private Spinner spnNegocio;
    private EditText txtFecha;
    private ImageView imgCalendar;
    private EditText txtHora;
    private ImageView imgClock;
    private Button btnAgregar;
    private Button btnCancelar;

    private Context context;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_crear_actividad, null);

        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtTipo = view.findViewById(R.id.txtTipo);
        spnOrganizacion = view.findViewById(R.id.spnOrganizacion);
        spnPersona = view.findViewById(R.id.spnPersona);
        spnNegocio = view.findViewById(R.id.spnNegocio);
        txtFecha = view.findViewById(R.id.txtFecha);
        imgCalendar = view.findViewById(R.id.imgCalendar);
        txtHora = view.findViewById(R.id.txtHora);
        imgClock = view.findViewById(R.id.imgClock);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnCancelar = view.findViewById(R.id.btnCancelar);

        //Lista de Organizaciones
        ArrayList<Organizacion> listaO = new OrganizacionTransactions(context).getListOrganizacionesSpn();
        Organizacion organizacion = new Organizacion(0, "Seleccione Organización:");
        listaO.add(0, organizacion);

        ArrayAdapter<Organizacion> adapterO = new ArrayAdapter<>(context,
                R.layout.support_simple_spinner_dropdown_item, listaO);
        spnOrganizacion.setAdapter(adapterO);

        //Lista de Personas
        ArrayList<Persona> listaP = new PersonaTransactions(context).getListPersonasSpn();
        Persona Persona = new Persona(0, "Seleccione Persona:");
        listaP.add(0, Persona);

        ArrayAdapter<Persona> adapterP = new ArrayAdapter<>(context,
                R.layout.support_simple_spinner_dropdown_item, listaP);
        spnPersona.setAdapter(adapterP);

        //Lista de Negocios
        ArrayList<Negocio> listaN = new NegocioTransactions(context).getListNegociosSpn();
        Negocio negocio = new Negocio(0, "Seleccione Negocio:");
        listaN.add(0, negocio);

        ArrayAdapter<Negocio> adapterN = new ArrayAdapter<>(context,
                R.layout.support_simple_spinner_dropdown_item, listaN);
        spnNegocio.setAdapter(adapterN);

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogFragment datePickerDialog = new DatePickerDialogFragment();
                datePickerDialog.show(getChildFragmentManager(), "datePicker");
            }
        });

        imgClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialogFragment timePickerDialog = new TimePickerDialogFragment();
                timePickerDialog.show(getChildFragmentManager(), "timePicker");
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDescripcion = txtDescripcion.getText().toString();
                String strTipo = txtTipo.getText().toString();
                int idOrganizacion = ((Organizacion) spnOrganizacion.getSelectedItem()).getId();
                int idPersona = ((Persona) spnPersona.getSelectedItem()).getId();
                int idNegocio = ((Negocio) spnNegocio.getSelectedItem()).getId();
                String strFecha = txtFecha.getText().toString();
                String strHora = txtHora.getText().toString();

                if(!strDescripcion.equals("") || !strTipo.equals("") || idOrganizacion != 0 || idPersona != 0 ||
                        idNegocio != 0 || !strFecha.equals("") || !strHora.equals("")) {
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
                final String strDescripcion = txtDescripcion.getText().toString();
                final String strTipo = txtTipo.getText().toString();
                final int idOrganizacion = ((Organizacion) spnOrganizacion.getSelectedItem()).getId();
                final int idPersona = ((Persona) spnPersona.getSelectedItem()).getId();
                final int idNegocio = ((Negocio) spnNegocio.getSelectedItem()).getId();
                final String strFecha = txtFecha.getText().toString();
                final String strHora = txtHora.getText().toString();

                //Validar que haya ingresado todos los datos
                if (strDescripcion.equals("") || strTipo.equals("") || strFecha.equals("") || strHora.equals("")) {
                    Toast.makeText(context, "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Crear Persona en DB
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("¿ESTÁ SEGURO(A) DE GUARDAR CAMBIOS?");
                alertDialogBuilder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int id) {
                        Actividad actividad = new Actividad();
                        actividad.setDescripcion(strDescripcion);
                        actividad.setTipo(strTipo);
                        actividad.setOrganizacion(idOrganizacion == 0 ? null : new Organizacion(idOrganizacion));
                        actividad.setPersona(idPersona == 0 ? null : new Persona(idPersona));
                        actividad.setNegocio(idNegocio == 0 ? null : new Negocio(idNegocio));
                        actividad.setFecha(strFecha);
                        actividad.setHora(strHora);

                        new ActividadTransactions(context).insertActividad(actividad);

                        Toast.makeText(context, "Actividad creada exitosamente", Toast.LENGTH_SHORT).show();
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

    public void setDate(String date) {
        txtFecha.setText(date);
    }

    public void setTime(String time) {
        txtHora.setText(time);
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

                    ((CrearActividadDialogFragment) getParentFragment()).setDate(date);

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

    public static class TimePickerDialogFragment extends DialogFragment {
        private TimePicker timePicker;
        private Button btnAceptar;
        private Button btnCancelar;

        private Context context;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_time, null);

            timePicker = view.findViewById(R.id.time);
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
                    String hour = (timePicker.getCurrentHour() < 10 ? "0" : "") + String.valueOf(timePicker.getCurrentHour());
                    String day = (timePicker.getCurrentMinute() < 10 ? "0" : "") + String.valueOf(timePicker.getCurrentMinute());

                    String time = hour + ":" + day;

                    ((CrearActividadDialogFragment) getParentFragment()).setTime(time);

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
