package Modelo

object FactoriaListaFrutas {
    fun generaLista(cant:Int):ArrayList<Fruta> {
        var lista = ArrayList<Fruta>(1)
        for(i in 1..cant){
            lista.add(FactoriaFruta.generaFruta())
        }
        return lista
    }
}