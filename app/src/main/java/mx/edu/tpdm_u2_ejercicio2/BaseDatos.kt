package mx.edu.tpdm_u2_ejercicio2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int  //Incrementa versión al hacer cambios en la esctructura.
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(p0: SQLiteDatabase?) {
        //SE EJECUTA UNICA Y EXCLUSIVAMENTE LA 1RA VEZ QUE LA APP SE EJECUTA EN UN CEL/TABLETA
        p0?.execSQL("CREATE TABLE EVENTOS(ID INTEGER, DESCRIPCION VARCHAR(500), FECHA VARCHAR(50), PRECIO FLOAT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}