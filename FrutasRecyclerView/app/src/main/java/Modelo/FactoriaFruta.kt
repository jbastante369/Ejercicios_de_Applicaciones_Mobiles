package Modelo

object FactoriaFruta {
    fun generaFruta() : Fruta{
        var nombres = listOf<String>("Manzana", "Pera", "Limon", "Platano", "Coco","Kiwi")
        var imagenes = listOf<String>("manzana","pera","limon","platano","coco","kiwi");
        var indice = (nombres.indices).random()
        var nombreFruta = nombres[indice]
        var fruta = Fruta(nombreFruta,imagenes[indice])

        return fruta
    }
}