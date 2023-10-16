package com.example.formulario2_julian

import java.util.ArrayList

object Almacen_De_Usuarios {
    var usuarios = ArrayList<Usuario>()
    fun aniadirUsuario(u:Usuario){
        usuarios.add(u)
    }
}