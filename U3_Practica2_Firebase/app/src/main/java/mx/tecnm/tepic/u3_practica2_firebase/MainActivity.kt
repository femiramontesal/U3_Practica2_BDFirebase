package mx.tecnm.tepic.u3_practica2_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.view.*
import mx.tecnm.tepic.u3_practica2_firebase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reservar.setOnClickListener {

            val lugar = binding.lugar.text.toString()
            val hora = binding.hora.text.toString()
            val fecha = binding.fecha.text.toString()
            val descripcion = binding.descripcion.text.toString()
            val cliente = binding.cliente.text.toString()

            database = FirebaseDatabase.getInstance().getReference("eventos")

            val Evento = Evento(lugar,hora,fecha,descripcion,cliente)
            database.child(cliente).setValue(Evento).addOnSuccessListener {
                binding.lugar.text.clear()
                binding.hora.text.clear()
                binding.fecha.text.clear()
                binding.descripcion.text.clear()
                binding.cliente.text.clear()

                Toast.makeText(this,"Evento guardado",Toast.LENGTH_LONG).show()


            }.addOnFailureListener{

                Toast.makeText(this,"Algo falló ",Toast.LENGTH_LONG).show()

            }

        }

        binding.buscar.setOnClickListener {
            var activity2 = Intent(this, MainActivity2::class.java)
            startActivity(activity2)
        }
    }
}