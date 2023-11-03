package Auxiliar

import Conexion.AdminSQLIteConexion
import Modelo.Fruta
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    //Clase tipo Singleton para acceder e métodos sin tener que crear objeto (similar a Static de Java)
    //Si hay algún cambio en la BBDD, se cambia el número de versión y así automáticamente
    // se pasa por el OnUpgrade del AdminSQLite
    //y ahí añades las sentencias que interese modificar de la BBDD
    private  var DATABASE_NAME = "frutas.db3"
    private  var DATABASE_VERSION = 1


    fun cambiarBD(nombreBD:String){
        this.DATABASE_NAME = nombreBD
    }

    fun addFruta(contexto: AppCompatActivity, f: Fruta):Long{
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("id", f.id)
        registro.put("nombre",f.nombre)
        registro.put("imagen", f.imagen)
        val codigo = bd.insert("frutas", null, registro)
        bd.close()
        return codigo
    }

    fun delFruta(contexto: AppCompatActivity, id: String):Int{
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val cant = bd.delete("frutas", "id=?", arrayOf(id))
        bd.close()
        return cant
    }

    fun modFruta(contexto:AppCompatActivity, id:String, f:Fruta):Int {
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nombre", f.nombre)
        registro.put("imagen", f.imagen)
        val cant = bd.update("frutas", registro, "id=?", arrayOf(id))
        bd.close()
        return cant
    }

    fun buscarFruta(contexto: AppCompatActivity, id:String):Fruta? {
        var f:Fruta? = null //si no encuentra ninguno vendrá null, por eso la ? y también en la devolución de la función.
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.readableDatabase
        /*Esta funciona pero es mejor como está hecho justo debajo con ?
        val fila = bd.rawQuery(
            "select nombre,edad from personas where dni='${dni}'",
            null
        )*/
        val fila =bd.rawQuery(
            "SELECT nombre, imagen FROM frutas WHERE id=?",
            arrayOf(id)
        )
        //en fila viene un CURSOR, que está justo antes del primero por eso lo ponemos en el primero en la siguiente línea
        if (fila.moveToFirst()) {//si no hay datos el moveToFirst, devuelve false, si hay devuelve true.
            f = Fruta(id, fila.getString(0), fila.getString(1))
        }
        bd.close()
        return f
    }

    fun obtenerFrutas(contexto: AppCompatActivity):ArrayList<Fruta>{
        var frutas:ArrayList<Fruta> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.readableDatabase
        val fila = bd.rawQuery("select id,nombre,imagen from frutas", null)
        while (fila.moveToNext()) {
            var f:Fruta = Fruta(fila.getString(0),fila.getString(1),fila.getString(2))
            frutas.add(f)
        }
        bd.close()
        return frutas //este arrayList lo puedo poner en un adapter de un RecyclerView por ejemplo.
    }

}