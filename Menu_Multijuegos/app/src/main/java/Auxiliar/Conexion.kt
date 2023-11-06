package Auxiliar

import Conexion.AdminSQLIteConexion
import Modelo.Usuario
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    private  var DATABASE_NAME = "usuarios.db3"
    private  var DATABASE_VERSION = 1


    fun cambiarBD(nombreBD:String){
        this.DATABASE_NAME = nombreBD
    }

    fun addUsuario(contexto: AppCompatActivity, u: Usuario):Long{
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nombre",u.nombre)
        registro.put("nivel", u.nivel.toString())
        val codigo = bd.insert("usuarios", null, registro)
        bd.close()
        return codigo
    }

    fun delUsuario(contexto: AppCompatActivity, nombre: String):Int{
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val cant = bd.delete("usuarios", "nombre=?", arrayOf(nombre.toString()))
        bd.close()
        return cant
    }

    fun modUsuario(contexto:AppCompatActivity, nombre:String, u:Usuario):Int {
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nivel", u.nivel)
        val cant = bd.update("usuarios", registro, "nombre=?", arrayOf(nombre.toString()))
        bd.close()
        return cant
    }

    fun buscarUsuario(contexto: AppCompatActivity, nombre:String):Usuario? {
        var u:Usuario? = null
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.readableDatabase
        val fila =bd.rawQuery(
            "SELECT nivel FROM usuarios WHERE nombre=?",
            arrayOf(nombre.toString())
        )
        if (fila.moveToFirst()) {
            u = Usuario(nombre, fila.getInt(0))
        }
        bd.close()
        return u
    }

    fun obtenerUsuario(contexto: AppCompatActivity):ArrayList<Usuario>{
        var usuarios:ArrayList<Usuario> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.readableDatabase
        val fila = bd.rawQuery("select nombre,nivel from usuarios", null)
        while (fila.moveToNext()) {
            var u:Usuario = Usuario(fila.getString(0),fila.getInt(1))
            usuarios.add(u)
        }
        bd.close()
        return usuarios
    }

}