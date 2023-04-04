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
import com.example.desafiopractico2.Models.Historial
import com.example.desafiopractico2.Models.Medicamentos
import com.example.desafiopractico2.R
import com.example.desafiopractico2.selectCompra

class MyAdapterHistorial : RecyclerView.Adapter<MyAdapterHistorial.myViewHolder>() {

    private val historialList = ArrayList<Historial>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemhistorial, parent, false
        )
        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return historialList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        //aca hago match con lo que tenga la vista y regresa el viewholder(firebase data)
        val currentItem = historialList[position]
        holder.medicamentoName.text = currentItem.medicamento
        holder.medicamentoPrecioUnitario.text = "$ "+ currentItem.medicamentoTotal
        holder.medicamentoPrecio.text = "$ "+ currentItem.medicamentoPrecio
        holder.medicamentoCantidad.text = currentItem.medicamentoCantidadl
        holder.medicamentoDireccion.text = currentItem.mdirecionEnvio



    }

    fun updateHistorialList(historialList: List<Historial>) {
        this.historialList.clear()
        this.historialList.addAll(historialList)
        notifyDataSetChanged()
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicamentoName: TextView = itemView.findViewById(R.id.medicamentoName)
        val medicamentoPrecioUnitario: TextView = itemView.findViewById(R.id.medicamentoPrecioUnitario)
        val medicamentoPrecio: TextView = itemView.findViewById(R.id.medicamentoPrecio)
        val medicamentoCantidad: TextView =
            itemView.findViewById(R.id.medicamentoCantidad)
        val medicamentoDireccion: TextView =
            itemView.findViewById(R.id.medicamentoDireccion)
    }

}