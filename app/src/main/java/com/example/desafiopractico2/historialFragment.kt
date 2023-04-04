package com.example.desafiopractico2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopractico2.Adapter.MyAdapterHistorial
import com.example.desafiopractico2.Models.HistorialViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var viewModel: HistorialViewModel
private lateinit var ReciclerViewHistorialList: RecyclerView
lateinit var MyAdapterHistorial: MyAdapterHistorial

/**
 * A simple [Fragment] subclass.
 * Use the [historialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class historialFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historial, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment historialFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            historialFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ReciclerViewHistorialList = view.findViewById(R.id.ReciclerViewHistorialList)
        ReciclerViewHistorialList.layoutManager = LinearLayoutManager(context)
        ReciclerViewHistorialList.setHasFixedSize(true)
        MyAdapterHistorial = MyAdapterHistorial()
        ReciclerViewHistorialList.adapter = MyAdapterHistorial

        viewModel = ViewModelProvider(this).get(HistorialViewModel::class.java)

        viewModel.allHistorial.observe(viewLifecycleOwner, { MyAdapterHistorial.updateHistorialList((it)) })
    }

}