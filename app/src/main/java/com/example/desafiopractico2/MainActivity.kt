package com.example.desafiopractico2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.desafiopractico2.databinding.ActivityMainBinding
import com.example.desafiopractico2.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        replaceFragmentView(medicamentosFragment()) //aca seteo un fragment por default

        binding.textView7.text="Usuario activo: "+ (firebaseAuth.currentUser?.email ?: "")
        binding.bottonNavigationMenu.setOnItemSelectedListener{
            when(it.itemId){
                R.id.medicamentosMenuItem -> replaceFragmentView(medicamentosFragment())
                R.id.historialMenuItem -> replaceFragmentView(historialFragment())
                R.id.logoutMenuItem -> logOut()

                else ->{}
            }
            true
        }
    }

    private fun replaceFragmentView(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransition=fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.homeframeLayout,fragment)
        fragmentTransition.commit()
    }

    private fun logOut(){
        firebaseAuth.signOut()
        val intent = Intent(this, SiginActivity::class.java)
        startActivity(intent)
    }


}