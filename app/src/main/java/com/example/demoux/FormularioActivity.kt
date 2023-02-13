package com.example.demoux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.demoux.databinding.ActivityFormularioBinding
import com.example.demoux.models.TiposDeCuenta
import com.example.demoux.utils.EventosInputsLayout
import com.google.android.material.textfield.TextInputLayout


class FormularioActivity : AppCompatActivity() {

    lateinit var binding: ActivityFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()
        binding.txtCorreo.setOnEditorActionListener(TextView.OnEditorActionListener{tv, actionId, event ->

            if(actionId == EditorInfo.IME_ACTION_DONE) {

                if(binding.tilCorreo.isErrorEnabled||
                    binding.tilApellido.isErrorEnabled||
                    binding.tilCorreo.isErrorEnabled ){
                    Toast.makeText(applicationContext, "Correcto", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext, "Incorrrecto", Toast.LENGTH_SHORT).show()
                }
            }
            false



        })

    }


    fun setUpUi(){

        addErrorInputLayout(binding.tilNombre,EventosInputsLayout.ERROR_TRES_CARACTERES)
        addErrorInputLayout(binding.tilApellido,EventosInputsLayout.ERROR_TRES_CARACTERES)
        addErrorInputLayout(binding.tilCorreo,EventosInputsLayout.ERROR_CORREO_NO_VALIDO)

        EventosInputsLayout.addTextChanguedListener(binding.txtNombre,binding.tilNombre)
        EventosInputsLayout.addTextChanguedListener(binding.txtapellido,binding.tilApellido)
        EventosInputsLayout.correoAddTextChanguedListener(binding.txtCorreo,binding.tilCorreo)


            /*binding.txtBuscar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(applicationContext, "$query", Toast.LENGTH_SHORT).show()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })*/


        // SPINER CON RECURSO XML
        val spinMesesAdapter = ArrayAdapter.createFromResource(this,R.array.meses_array, android.R.layout.simple_spinner_item)
        spinMesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinMeses.adapter=spinMesesAdapter

        val arrayTiposCuenta= TiposDeCuenta.obtenerTiposCuenta()

        // SPINER CON ONJETO
        val spinCuentasAdapter = ArrayAdapter<TiposDeCuenta>(this, android.R.layout.simple_spinner_item, arrayTiposCuenta)
        spinCuentasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinCuentas.adapter=spinCuentasAdapter

        //SETEAR VALOR POR DEFAULT DEL SPINNER CON LA POSICION
        //val cuentaCheques = arrayTiposCuenta[2]
        binding.spinCuentas.post ( Runnable { binding.spinCuentas.setSelection(2) } )

    }

    private fun addErrorInputLayout(t:TextInputLayout, mensaje:String){

        t.isErrorEnabled = true
        t.error= mensaje
    }

    fun obtenerCuenta(view: android.view.View) {

        var mCuenta=binding.spinCuentas.selectedItem as TiposDeCuenta
        Toast.makeText(applicationContext, "Selecciona cuenta - ${mCuenta.descripcion}", Toast.LENGTH_SHORT).show()

    }
}