package com.example.desafiopractico2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


lateinit var ordenMedicamentoName: TextView
lateinit var ordenMedicamentoPrecio: TextView
lateinit var ordenMedicamentoCantidad: EditText
lateinit var ordenMedicamentoTotalIva: TextView
lateinit var ordenMedicamentoTotal: TextView
lateinit var cardDirrecion: TextView
lateinit var textoUsuario: TextView

lateinit var cardNumber: EditText
lateinit var cardMes: EditText
lateinit var cardaño: EditText
lateinit var cardCvv: EditText
lateinit var buttonPagar: Button
var iva = 0
var precioTotal = 0


class selectCompra : AppCompatActivity() {
    var firebaseAuth = FirebaseAuth.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectcompra)
        var compraData: compraData
        compraData = compraData()

        ordenMedicamentoName = findViewById(R.id.ordenMedicamentoName)
        ordenMedicamentoPrecio = findViewById(R.id.ordenMedicamentoPrecio)
        ordenMedicamentoCantidad = findViewById(R.id.ordenMedicamentoCantidad)
        ordenMedicamentoTotalIva = findViewById(R.id.ordenMedicamentoTotalIva)
        ordenMedicamentoTotal = findViewById(R.id.ordenMedicamentoTotal)
        textoUsuario=findViewById(R.id.textView7)
        cardDirrecion = findViewById(R.id.cardDirrecion)
        cardNumber = findViewById(R.id.cardNumber)
        cardMes = findViewById(R.id.cardMes)
        cardaño = findViewById(R.id.cardaño)
        cardCvv = findViewById(R.id.cardCvv)
        buttonPagar = findViewById(R.id.buttonPagar)

        val bundle = intent.extras
        val medicamentNameIntent = bundle?.getString("medicamentoName")
        val medicamentPriceIntent = bundle?.getString("medicamentoPrice")


        ordenMedicamentoName.setText(medicamentNameIntent)
        ordenMedicamentoPrecio.setText("$ " + (medicamentPriceIntent + ".00"))
        ordenMedicamentoCantidad.setText("1")
        textoUsuario.setText("Compra para el usuario: "+ (firebaseAuth.currentUser?.email ?:"" ))

        var precio = medicamentPriceIntent?.toInt() ?: 1
        var cantidadPrecio = (medicamentPriceIntent?.toInt() ?: 1) * 1
        var precioIva = cantidadPrecio * 0.13
        var cantidadTotal = cantidadPrecio + precioIva
        var cantidad = 1
        ordenMedicamentoTotalIva.setText("$ " + precioIva.toString())
        ordenMedicamentoTotal.setText("$ " + cantidadTotal.toString())

        ordenMedicamentoCantidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    precio = (medicamentPriceIntent?.toInt() ?: 1) * 1
                    cantidad = p0.toString().toInt()
                    cantidadPrecio = (precio?.toInt() ?: 1) * cantidad
                    precioIva = cantidadPrecio * 0.13
                    cantidadTotal = cantidadPrecio + precioIva
                    ordenMedicamentoTotalIva.setText("$ " + precioIva.toString())
                    ordenMedicamentoTotal.setText("$ " + cantidadTotal.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        buttonPagar.setOnClickListener {


            if (validarDatosDeTarjeta()) {
                //aca va la logica para firebase
                var firebaseAuth = FirebaseAuth.getInstance()
                var firebaseDatabase = FirebaseDatabase.getInstance()
                var firebaseReference = firebaseDatabase.getReference()

                //object
                compraData.setmedicamento(medicamentNameIntent)
                compraData.setmedicamentoCantidad(cantidad.toString())
                compraData.setmedicamentoPrecio(precio.toString())
                compraData.setmedicamentoTotal(cantidadTotal.toString())
                compraData.setdirecionEnvio(cardDirrecion.text.toString())
                compraData.setusuarioEmail(firebaseAuth.currentUser?.email)

                //aca va el push
                firebaseReference.child("historial").push().setValue(compraData)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, compraExitosa::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Ha ocurrido un problema con la compra. Detalles:" + it.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

        }
    }

    fun validarDatosDeTarjeta(): Boolean {
        var esValido: Boolean = true;

        if (cardDirrecion.getText().toString().isEmpty()) {
            cardDirrecion.setError("Este campo es requerido")
            esValido = false
        }

        if (cardNumber.getText().toString().isEmpty() || cardNumber.getText()
                .toString().length < 16
        ) {
            cardNumber.setError("Este campo es requerido y debe poseer 16 caracteres")
            esValido = false
        }
        if (cardMes.getText().toString().isEmpty() || Integer.parseInt(
                cardMes.getText().toString().trim()
            ) <= 0
        ) {
            cardMes.setError("Este campo es requerido")
            esValido = false
        }
        if (cardaño.getText().toString().trim()
                .isEmpty() || Integer.parseInt(
                cardaño.getText().toString().trim()
            ) <= 0
        ) {
            cardaño.setError("Este campo es requerido y debe ser mayor a 0")
            esValido = false
        }
        if (cardCvv.getText().toString().trim()
                .isEmpty() || Integer.parseInt(
                cardCvv.getText().toString().trim()
            ) <= 0 || cardCvv.getText().toString().length < 3
        ) {
            cardCvv.setError("Este campo es requerido y debe ser mayor a 3 diguitos")
            esValido = false
        }
        return esValido;
    }
}