package com.example.unica.pruebaandroidnapps.db;

import android.provider.BaseColumns;

public final class PruebaTables  {

    public PruebaTables() {
    }

    public static abstract class PersonasTable implements BaseColumns{
        public static final String TABLE_NAME = "PERSONAS";
        public static final String ID = "ID";
        public static final String NOMBRE = "NOMBRE";
        public static final String TELEFONO = "TELEFONO";
        public static final String EMAIL = "EMAIL";

       static final String CREATE =
               "CREATE TABLE " + TABLE_NAME + " (" +
               ID + " INTEGER PRIMARY KEY, " +
               NOMBRE + " TEXT, " +
               TELEFONO + " TEXT, " +
               EMAIL + " TEXT " +
               ")";
    }

    public static abstract class OrganizacionesTable implements BaseColumns{
        public static final String TABLE_NAME = "ORGANIZACIONES";
        public static final String ID = "ID";
        public static final String NOMBRE = "NOMBRE";
        public static final String DIRECCION = "DIRECCION";
        public static final String TELEFONO = "TELEFONO";

        static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NOMBRE + " TEXT, " +
                DIRECCION + " TEXT, " +
                TELEFONO + " TEXT " +
                ")";
    }

    public static abstract class NegociosTable implements BaseColumns {
        public static final String TABLE_NAME = "NEGOCIOS";
        public static final String ID = "ID";
        public static final String TITULO = "TITULO";
        public static final String DESCRIPCION = "DESCRIPCION";
        public static final String ORGANIZACION = "ID_ORGANIZACION";
        public static final String PERSONA = "ID_PERSONA";
        public static final String VALOR = "VALOR";
        public static final String FECHA_CIERRE = "FECHA_CIERRE";
        public static final String ESTADO = "ESTADO";

        static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                TITULO + " TEXT, " +
                DESCRIPCION + " TEXT, " +
                ORGANIZACION + " INTEGER, " +
                PERSONA + " INTEGER, " +
                VALOR + " TEXT, " +
                FECHA_CIERRE + " TEXT, " +
                ESTADO + " TEXT" +
                ")";
    }

    public static abstract class ActividadesTable implements BaseColumns{
        public static final String TABLE_NAME = "ACTIVIDADES";
        public static final String ID = "ID";
        public static final String DESCRIPCION = "DESCRIPCION";
        public static final String TIPO = "TIPO";
        public static final String ORGANIZACION = "ID_ORGANIZACION";
        public static final String PERSONA = "ID_PERSONA";
        public static final String NEGOCIO = "ID_NEGOCIO";
        public static final String FECHA = "FECHA";
        public static  final String HORA = "HORA";

        static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY, " +
                        DESCRIPCION + " TEXT, " +
                        TIPO + " TEXT, " +
                        ORGANIZACION + " INTEGER, " +
                        PERSONA + " INTEGER, " +
                        NEGOCIO + " INTEGER, " +
                        FECHA + " TEXT, " +
                        HORA + " TEXT" +
                        ")";

    }
}
