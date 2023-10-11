package modelo

import java.util.ArrayList

object Almacen_personas {
    var personas = ArrayList<Persona>()

    fun aniadirPersona(p:Persona){
        personas.add(p)
    }
}