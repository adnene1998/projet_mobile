package com.example.mini_projet

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mini_projet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnConn.setOnClickListener {
            val loginText = binding.login.text.toString()
            val passwordText = binding.password.text.toString()

            if (loginText == "client" && passwordText == "1234") {
                Toast.makeText(this, "Bienvenue", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Accueil::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Mot de passe ou login est incorrect", Toast.LENGTH_SHORT).show()
            }
        }


    }
}