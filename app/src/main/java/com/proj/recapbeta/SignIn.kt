package com.proj.recapbeta

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.proj.recapbeta.databinding.ActivitySigninBinding

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var studentRb: RadioButton
    lateinit var teacherRb: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentRb = findViewById(R.id.studentRb)
        teacherRb = findViewById(R.id.teacherRb)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.tosignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        binding.signinBtn.setOnClickListener {
            val email = binding.usernameSi.text.toString()
            val pass = binding.passwordSi.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful && studentRb.isChecked) {
                        //to Course Catalog
                        // Lagay lang here for logout, wala pang logout sa course cat
                        val intent = Intent(this, CourseCatalog::class.java)
                        startActivity(intent)
                    } else if(it.isSuccessful && teacherRb.isChecked){
                        val teachIntent = Intent(this, TeacherPage::class.java)
                        startActivity(teachIntent)
                    }
                    else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}