package com.example.desafiopractico2.Adapter

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopractico2.Models.Medicamentos
import com.example.desafiopractico2.R

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