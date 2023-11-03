package Adaptadores

import Modelo.AlmacenDeFrutas
import Modelo.Fruta
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.frutasrecycleview_database.MainActivity
import com.example.frutasrecycleview_database.MainActivity2
import com.example.frutasrecycleview_database.R

class MiAdaptador (var frutas : ArrayList<Fruta>, var  context: Context) : RecyclerView.Adapter<MiAdaptador.ViewHolder>(){

    companion object {
        var seleccionado:Int = -1
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = frutas.get(position)
        holder.bind(item, context, position, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        val viewHolder = ViewHolder(vista)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            context.startActivity(intent)
        }

        return viewHolder
    }
    override fun getItemCount(): Int {
        return frutas.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val nombreFruta = view.findViewById(R.id.txtNombre) as TextView
        val idFruta = view.findViewById(R.id.txtId) as TextView
        val imagen = view.findViewById(R.id.imgFruta) as ImageView

        val btnDetalleEspcifico = view.findViewById<Button>(R.id.btDetalleCard) as Button
        @SuppressLint("ResourceAsColor")
        fun bind(fruta: Fruta, context: Context, pos: Int, miAdaptador: MiAdaptador){
            nombreFruta.text = fruta.nombre
            idFruta.text = fruta.id

            var imagenes = listOf<String>("manzana","pera","limon","platano","coco","kiwi");
            if (fruta.imagen in imagenes){
                val uri = "@drawable/" + fruta.imagen
                val imageResource: Int = context.getResources().getIdentifier(uri, null, context.getPackageName())
                var res: Drawable = context.resources.getDrawable(imageResource)
                imagen.setImageDrawable(res)
            }else{
                val uri = "@drawable/ic_default"
                val imageResource: Int = context.getResources().getIdentifier(uri, null, context.getPackageName())
                var res: Drawable = context.resources.getDrawable(imageResource)
                imagen.setImageDrawable(res)
            }


            if (pos == MiAdaptador.seleccionado) {
                with(nombreFruta) {
                    this.setTextColor(resources.getColor(R.color.green))
                }
                with(idFruta) {
                    this.setTextColor(resources.getColor(R.color.green))
                }
            }
            else {
                with(nombreFruta) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
                with(idFruta) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
            }

            itemView.setOnClickListener {
                if (pos == MiAdaptador.seleccionado){
                    MiAdaptador.seleccionado = -1
                }
                else {
                    MiAdaptador.seleccionado = pos
                }

                miAdaptador.notifyDataSetChanged()
            }


            /*btnDetalleEspcifico.setOnClickListener {
                Log.e("JBS","Has pulsado el botón de ${fruta}")
                var inte : Intent = Intent(MainActivity.contextoPrincipal, MainActivity2::class.java)
                inte.putExtra("obj",fruta)
                ContextCompat.startActivity(MainActivity.contextoPrincipal, inte, null)
            }*/
        }
    }
}