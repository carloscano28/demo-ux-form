package com.example.demoux.models

class TiposDeCuenta(val idTipo:Int, val descripcion:String) {

    companion object{

        fun obtenerTiposCuenta():ArrayList<TiposDeCuenta>{


            return arrayListOf<TiposDeCuenta>(
                TiposDeCuenta(1,"Debito") ,
                TiposDeCuenta(2,"Credito"),
                TiposDeCuenta(3,"Cheques")

            )
        }

    }

    override fun toString():String {

        return " $idTipo - $descripcion"
    }

}

