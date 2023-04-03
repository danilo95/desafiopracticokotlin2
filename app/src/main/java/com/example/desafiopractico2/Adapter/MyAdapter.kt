package com.example.desafiopractico2.Adapter

import android.content.Intent
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopractico2.Models.Medicamentos
import com.example.desafiopractico2.R
import com.example.desafiopractico2.selectCompra

class MyAdapter : RecyclerView.Adapter<MyAdapter.myViewHolder>() {

    private val medicamentosList = ArrayList<Medicamentos>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item, parent, false
        )
        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return medicamentosList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        //aca hago match con lo que tenga la vista y regresa el viewholder(firebase data)
        val currentItem = medicamentosList[position]
        holder.medicamentoName.text = currentItem.nombre
        holder.medicamentoPrice.text = "$ "+ DecimalFormat("#.00").format(currentItem.precio)
        holder.medicamentoIndications.text = currentItem.indicaciones
        holder.medicamentoConIndications.text = currentItem.contraIndicaciones


        val test: Button = holder.itemView.findViewById((R.id.buttonselect))

        test.setOnClickListener { v: View ->

            Toast.makeText(holder.itemView.context, "soy yo ${currentItem}", Toast.LENGTH_SHORT)
                .show()


            val intent: Intent = Intent(v.context, selectCompra::class.java)

            v.context.startActivity(intent)


        }
        val compra: Button = holder.itemView.findViewById((R.id.buttoncompra))

        test.setOnClickListener { v: View ->

            Toast.makeText(holder.itemView.context, "soy yo ${currentItem}", Toast.LENGTH_SHORT)
                .show()


            val intent: Intent = Intent(v.context, compra::class.java)

            v.context.startActivity(intent)


        }


    }

    fun updateMedicamentosList(medicamentosList: List<Medicamentos>) {
        this.medicamentosList.clear()
        this.medicamentosList.addAll(medicamentosList)
        notifyDataSetChanged()
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicamentoName: TextView = itemView.findViewById(R.id.medicamentoName)
        val medicamentoPrice: TextView = itemView.findViewById(R.id.medicamentoPrice)
        val medicamentoIndications: TextView = itemView.findViewById(R.id.medicamentoIndications)
        val medicamentoConIndications: TextView =
            itemView.findViewById(R.id.medicamentoConIndications)
    }

}