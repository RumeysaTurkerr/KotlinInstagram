package com.rumeysa.kotlininstagram.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rumeysa.kotlininstagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth :FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val currentUser=auth.currentUser

        if(currentUser !=null){
            val intent=Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signInClicked(view : View){
        val email = binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent=Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this@MainActivity, it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        else
            Toast.makeText(this,"Enter email and password!", Toast.LENGTH_LONG).show()

    }

    @SuppressLint("SuspiciousIndentation")
    fun signUpClicked(view : View){
     val email = binding.emailText.text.toString()
     val password=binding.passwordText.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent=Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this@MainActivity, it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        else
            Toast.makeText(this,"Enter email and password!", Toast.LENGTH_LONG).show()

    }

}