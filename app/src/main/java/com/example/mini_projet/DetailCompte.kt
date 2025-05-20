package com.example.mini_projet

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mini_projet.databinding.ActivityDetailCompteBinding

class DetailCompte : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCompteBinding

    data class Operation(
        val date: String,
        val description: String,
        val montant: Double,
        val isDebit: Boolean
    )

    inner class OperationAdapter(operations: List<Operation>) :
        ArrayAdapter<Operation>(this, R.layout.item_operation, operations) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: layoutInflater.inflate(R.layout.item_operation, parent, false)

            val textDateDescription = view.findViewById<TextView>(R.id.tvDateDescription)
            val textMontant = view.findViewById<TextView>(R.id.tvMontant)

            val operation = getItem(position)!!

            textDateDescription.text = "${operation.date} - ${operation.description}"

            val montantFormate = String.format("%.2f €", operation.montant)
            val montantText = if (operation.isDebit) "-$montantFormate" else "+$montantFormate"
            textMontant.text = montantText

            textMontant.setTextColor(
                if (operation.isDebit)
                    getColor(android.R.color.holo_red_dark)
                else
                    getColor(android.R.color.holo_green_dark)
            )

            return view
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCompteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val operations = listOf(
            Operation("01/04/2024", "Virement reçu ENTREPRISE", 1500.00, false),
            Operation("31/03/2024", "Paiement CB CARREFOUR", 45.30, true),
            Operation("30/03/2024", "Retrait DAB", 50.00, true)
        )

        binding.listViewOperations.adapter = OperationAdapter(operations)

        val soldeTotal = operations.sumOf { if (it.isDebit) -it.montant else it.montant }

        binding.tvNumeroCompte.text = getString(R.string.numero_compte, "1234 5678 9012 3456")
        binding.tvSolde.text = getString(R.string.solde_format, String.format("%.2f", soldeTotal))

        binding.btnVirement.setOnClickListener {
            // TODO: Implémenter la logique pour le virement
        }

        binding.btnRIB.setOnClickListener {
            // TODO: Implémenter la logique pour afficher le RIB
        }
    }
}