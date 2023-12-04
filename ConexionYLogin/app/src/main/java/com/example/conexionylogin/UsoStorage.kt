package com.example.conexionylogin

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.conexionylogin.databinding.ActivityUsoStorageBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.File

class UsoStorage : AppCompatActivity() {
    private lateinit var binding: ActivityUsoStorageBinding
    /*
    https://firebase.google.com/docs/storage/android/create-reference
    Crea una referencia para subir, descargar o borrar un archivo, o para obtener o actualizar sus metadatos.
    Se puede decir que una referencia es un indicador que apunta a un archivo en la nube. Las referencias son livianas,
    por lo que puedes crear todas las que necesites. También se pueden reutilizar en varias operaciones.
     */
    var storage = Firebase.storage
    // Crea una referencia con la instancia singleton FirebaseStorage y con una llamada al método reference.
    var storageRef = storage.reference
    val TAG = "ACSCO"
    private val cameraRequest = 1888
    private lateinit var bitmap : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_uso_storage)
        binding = ActivityUsoStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create a child reference
        // imagesRef now points to "images"
        var imagesRef: StorageReference? = storageRef.child("images")

        // Child references can also take paths
        // spaceRef now points to "images/space.jpg
        // imagesRef still points to "images"
        var spaceRef = storageRef.child("images/spock.jpg") //spock.jpg es el nombre de la imagen


        // parent allows us to move our reference to a parent node
        // imagesRef now points to 'images'
        imagesRef = spaceRef.parent
        Log.e(TAG, imagesRef!!.path.toString())

        // root allows us to move all the way back to the top of our bucket
        // rootRef now points to the root
        val rootRef = spaceRef.root
        Log.e(TAG, rootRef.path.toString())



        // File path is "images/spock.jpg"
        val path = spaceRef.path
        Log.e(TAG, path.toString())

        // File name is "spock.jpg"
        val name = spaceRef.name
        Log.e(TAG, name.toString())

        //Listar todos.
        val listRef = storage.reference.child("images")
        listRef.listAll()
            .addOnSuccessListener {
                Log.d(TAG,"Items en images")
                for (item in it.items) {
                    Log.e(TAG,item.toString())
                }
            }
            .addOnFailureListener {
                // Uh-oh, an error occurred!
            }

        binding.btCargar.setOnClickListener {
            fileUpload()
        }

        binding.btnDescargar.setOnClickListener {
            fileDownload()
        }

        binding.btnCargarCamara.setOnClickListener {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    /**
     * Método que descarga el fichero usando un archivo temporal.
     */
    fun fileDownload() {
        var nomImagen=binding.txtNombreImagen.text.toString()
        //var spaceRef = storageRef.child("images/saturno.webp")
        var spaceRef = storageRef.child("images/$nomImagen")

        val localfile  = File.createTempFile("tempImage","jpg")
        spaceRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.imgImagen.setImageBitmap(bitmap)
        }.addOnFailureListener{
            Toast.makeText(this,"Algo ha fallado en la descarga", Toast.LENGTH_SHORT).show()
        }

    }
    val openCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data!!
            this.bitmap = data.extras!!.get("data") as Bitmap
            binding.imgImagen.setImageBitmap(bitmap)
            fileUpload2()
        }
    }

    //Activity para pedir permisos de cámara.
    val requestCameraPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //si quisieras vídeo. Poner el punto y ver resto de opciones que ofrece, que prueben alguna.
            //val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            openCamera.launch(intent)
        } else {
            Log.e(TAG,"Permiso de cámara no concedido")
        }
    }


    /**
     * Subida de imagen. Seleccionamos de la galería de imágenes (ver ejemplo de cargar Fotos). Si queremos subir de la cámara ver ejemplo Fotos.
     */
    //------------------------- Funciones de callback para activities results -------------------------
    //Activity para lanzar la galería de imágenes.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.

        if (uri != null) {
            Log.d(TAG, "Selected URI: $uri")
            binding.imgImagen.setImageURI(uri)
            Log.d(TAG, "Cargada")
            val Folder: StorageReference =
                FirebaseStorage.getInstance().getReference().child("images")
//            val file_name: StorageReference = Folder.child("" + uri!!.lastPathSegment) //<-- Podemos coger el último segmento de toda la ruta como nombre.
            val file_name: StorageReference = Folder.child(binding.txtNombreImagen.text.toString())  //<-- Podemos poner el nombre que queramos. lo leo de la caja de texto.
            file_name.putFile(uri).addOnSuccessListener { taskSnapshot ->
                file_name.getDownloadUrl().addOnSuccessListener { uri ->
                    Toast.makeText(this,"Imagen ${uri.toString()} subida correctamente", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Log.d(TAG, "No media selected")
        }
    }


    fun fileUpload(){
        var nomImagen=binding.txtNombreImagen.text.toString()
        if(nomImagen==""){
            Toast.makeText(this,"Introduce un nombre para la imagen", Toast.LENGTH_SHORT).show()
        }
        else {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    fun fileUpload2(){
        var nomImagen=binding.txtNombreImagen.text.toString()
        if(nomImagen==""){
            Toast.makeText(this,"Introduce un nombre para la imagen", Toast.LENGTH_SHORT).show()
        }
        else {

        }
    }
}