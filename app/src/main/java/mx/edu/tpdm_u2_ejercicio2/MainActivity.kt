package mx.edu.tpdm_u2_ejercicio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.sql.SQLException

class MainActivity : AppCompatActivity() {

    var  insertar : Button ?= null
    var actualizar : Button ?= null
    var eliminar : Button ?= null
    var buscar : Button ?= null
    var clave : EditText ?= null
    var listado : ListView ?= null

    var basedatos = BaseDatos(this,"ejemplo2", null, 1)
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

        actualizar?.setOnClickListener {
            val ventanaActualizar = Intent(this, Main3Activity::class.java)
            startActivity(ventanaActualizar)
        }

        eliminar?.setOnClickListener {

        }

        buscar?.setOnClickListener {

        }

    }

    //FUNCIONES PARA BOTONES
    fun buscar(id:String, btnEtiqueta: String){
        try {
            var transaccion = basedatos.readableDatabase
            var SQL="SELECT *  FROM EVENTOS WHERE ID="+id

            var  respuesta= transaccion.rawQuery(SQL,null)

            if (respuesta.moveToFirst()==true){
                var cadena = "DESCRIPCION: " + respuesta.getString(1)+"\nFECHA: "+respuesta.getString(2)+"\nPRECIO "+respuesta.getString(3)

                //DEPENDIENDO EL BOTON PRESIONADO (BUSCAR, ELIMINAR O ACTUALIZAR) ES LA ACCION QUE REALIZARA
                if (btnEtiqueta.startsWith("Buscar")) {
                    //ListView donde se colocan los registros.
                }

                if (btnEtiqueta.startsWith("Eliminar")){
                    var cadena = "SEGURO QUE DESEA ELIMINAR A [ "+respuesta.getString(1)+" ] CON ID [ "+respuesta.getString(0)+" ]"
                    var alerta = AlertDialog.Builder(this)
                    AlertDialog.Builder(this)
                    alerta.setTitle("ATENCION").setMessage(cadena).setNeutralButton("NO"){dialog,which->
                        return@setNeutralButton
                    }.setPositiveButton("si"){dialog,which->
                        //eliminar(id)
                    }.show()
                }

                if(btnEtiqueta.startsWith("Actualizar")){
                    editDescripcion?.setText(respuesta.getString(0))
                    editFecha?.setText(respuesta.getString(1))
                    editPrecio?.setText(respuesta.getString(2))
                    actualizar?.setText("Aplicar cambios")
                    insertar?.setEnabled(false)
                    eliminar?.setEnabled(false)
                    buscar?.setEnabled(false)
                }
            }else{
                mensaje("ERROR","NO EXISTE EL ID")
            }
        }catch (err: SQLException){
            mensaje("ERROR","NO SE PUDO ENCONTRAR EL REGISTRO")
        }

    }

    //función para AlertDialogs
    fun mensaje(titulo:String, mensaje:String){
        AlertDialog.Builder(this).setTitle(titulo).setMessage(mensaje).setPositiveButton("Ok"){ dialog, which -> }.show()
    }

}
