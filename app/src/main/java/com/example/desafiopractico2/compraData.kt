package com.example.desafiopractico2

public class compraData {


    private var medicamento: String? = null
    private var medicamentoPrecio: String? = null
    private var medicamentoTotal: String? = null
    private var medicamentoCantidad: String? = null
    private var usuarioEmail: String? = null

    private var direcionEnvio: String? = null


    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    fun compraData() {}

    // created getter and setter methods
    // for all our variables.
    fun getmedicamento(): String? {
        return medicamento
    }

    fun setmedicamento(medicamento: String?) {
        this.medicamento = medicamento
    }

    fun getmedicamentoPrecio(): String? {
        return medicamentoPrecio
    }

    fun setmedicamentoPrecio(medicamentoPrecio: String?) {
        this.medicamentoPrecio = medicamentoPrecio
    }

    fun getmedicamentoTotal(): String? {
        return medicamentoTotal
    }

    fun setmedicamentoTotal(medicamentoTotal: String?) {
        this.medicamentoTotal = medicamentoTotal
    }

    fun getmedicamentoCantidadl(): String? {
        return medicamentoCantidad
    }

    fun setmedicamentoCantidad(medicamentoCantidad: String?) {
        this.medicamentoCantidad = medicamentoCantidad
    }

    fun getmusuarioEmail(): String? {
        return usuarioEmail
    }

    fun setusuarioEmail(usuarioEmail: String?) {
        this.usuarioEmail = usuarioEmail
    }

    fun getmdirecionEnvio(): String? {
        return direcionEnvio
    }

    fun setdirecionEnvio(direcionEnvio: String?) {
        this.direcionEnvio = direcionEnvio
    }
}