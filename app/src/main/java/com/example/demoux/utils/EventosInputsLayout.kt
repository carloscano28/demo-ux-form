package com.example.demoux.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import com.example.demoux.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

object EventosInputsLayout {

    val ERROR_TRES_CARACTERES="Al menos tres caracteres"
    val ERROR_CORREO_NO_VALIDO="No valido"

    fun addTextChanguedListener(e:EditText,t:TextInputLayout){
        e.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(texto: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val cadena = texto.toString()
                if(cadena.trim().isNullOrEmpty() || cadena.length <= 2){
                    t.error = ERROR_TRES_CARACTERES
                    t.isErrorEnabled = true
                }
                else{
                    t.isErrorEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    fun correoAddTextChanguedListener(e:EditText,t:TextInputLayout){
        e.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(texto: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val cadena = texto.toString()
                if(validarEmail(cadena)){
                    t.isErrorEnabled = false
                }else{
                    t.isErrorEnabled    = true
                    t.error             = ERROR_CORREO_NO_VALIDO
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }


    fun validarEmail(email:String):Boolean{

        val pattern:Pattern=Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()


    }

}
