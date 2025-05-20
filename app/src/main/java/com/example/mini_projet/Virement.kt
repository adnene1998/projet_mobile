package com.example.mini_projet

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mini_projet.databinding.ActivityMainBinding
import com.example.mini_projet.databinding.ActivityVirementBinding

class Virement : AppCompatActivity() {
    val binding = ActivityVirementBinding.inflate(layoutInflater)

    private val accueilLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Traiter le résultat
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnVirement.setOnClickListener {
            val montantText = binding.montant.text.toString()
            val ribText = binding.rib.text.toString()

            if (montantText.isNotEmpty() && ribText.isNotEmpty()) {

                Toast.makeText(
                    this,
                    "Virement de $montantText vers RIB $ribText effectué",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, Accueil::class.java)
                accueilLauncher.launch(intent)

            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}