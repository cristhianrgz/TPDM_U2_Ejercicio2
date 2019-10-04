package mx.edu.tpdm_u2_ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import java.sql.SQLException

class Main2Activity : AppCompatActivity() {
    var descripcion : EditText ?= null
    var fecha : EditText ?= null
    var precio : EditText ?= null
    var insertar : Button ?= null
    var regresar : Button ?= null
    var basedatos = BaseDatos(this,"ejemplo2", null, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        descripcion = findViewById(R.id.editDescripcion)
        fecha = findViewById(R.id.editFecha)
        precio = findViewById(R.id.editPrecio)
        insertar = findViewById(R.id.btnInsert)
        regresar = findViewById(R.id.btnRegresar)

        insertar?.setOnClickListener {
            insertar()
        }

        regresar?.setOnClickListener {
            finish()
        }
    }

    //FUNCIONES PARA BOTONES
    fun insertar(){
        try {
            var transacion = basedatos.writableDatabase
            var SQL = "INSERT INTO EVENTOS VALUES('DESCRIPCION', 'FECHA', 'PRECIO')"

            if(validarCampos() == false){
                mensaje("ERROR", "AL PARECER HAY UN CAMPO DE TEXTO VACIO")
                return
            }

            SQL = SQL.replace("DESCRIPCION",editDescripcion?.text.toString())
            SQL = SQL.replace("FECHA",editFecha?.text.toString())
            SQL = SQL.replace("PRECIO",editPrecio?.text.toString())

            transacion.execSQL(SQL)
            transacion.close()
            mensaje("EXITO", "SE INSERTO CORRECTAMENTE")
            limpiarCampos()
        }catch (err: SQLException){
            mensaje("Error", "NO SE PUDO INSERTAR TALVEZ EL ID YA EXISTE")
        }
    }

    //VALIDACIONES
    fun  validarCampos():Boolean{
        if(descripcion?.text!!.isEmpty() || fecha?.text!!.isEmpty() || precio?.text!!.isEmpty()){
            return false
        }else{
            return true
        }
    }

    fun validarCampo(campo: EditText): Boolean{
        if(campo.text.toString().isEmpty()){
            return false
        }else{
            return true
        }
    }

    fun limpiarCampos(){
        descripcion?.setText("")
        fecha?.setText("")
        precio?.setText("")
    }

    //función para AlertDialogs
    fun mensaje(titulo:String, mensaje:String){
        AlertDialog.Builder(this).setTitle(titulo).setMessage(mensaje).setPositiveButton("Ok"){ dialog, which -> }.show()
    }
}
