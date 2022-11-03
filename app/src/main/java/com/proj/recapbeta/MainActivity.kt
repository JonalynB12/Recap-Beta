package com.proj.recapbeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.proj.recapbeta.databinding.ActivityMainBinding
import com.proj.recapbeta.databinding.ActivitySignupBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            val intent= Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}