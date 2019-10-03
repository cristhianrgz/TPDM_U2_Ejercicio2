package mx.edu.tpdm_u2_ejercicio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var  insertar : Button ?= null
    var actualizar : Button ?= null
    var eliminar : Button ?= null
    var buscar : Button ?= null
    var clave : EditText ?= null
    var listado : ListView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insertar = findViewById(R.id.btnInsert)
        actualizar = findViewById(R.id.btnActualizar)
        eliminar = findViewById(R.id.btnEliminar)
        buscar = findViewById(R.id.btnBuscar)
        clave = findViewById(R.id.editClave)
        listado = findViewById(R.id.listaRegistros)

        insertar?.setOnClickListener {
            val ventanaInsert = Intent(this, Main2Activity::class.java)
            startActivity(ventanaInsert)
        }

    }
}
