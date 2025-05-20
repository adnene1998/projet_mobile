
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mini_projet.databinding.ActivityMdpOublieBinding

class mdp_oublie : AppCompatActivity() {
    private lateinit var binding: ActivityMdpOublieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMdpOublieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gestionnaire de clic pour le bouton d'envoi
        binding.btnSubmit.setOnClickListener {
            envoyerEmailResetPassword()
        }
    }

    private fun envoyerEmailResetPassword() {
        val emailAddress = binding.etEmail.text.toString().trim()


        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // Utilise seulement les applications email
                putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
                putExtra(Intent.EXTRA_SUBJECT, "Réinitialisation de mot de passe")
                putExtra(Intent.EXTRA_TEXT, """
                    Bonjour,
                    
                    Vous avez demandé la réinitialisation de votre mot de passe.
                    Veuillez cliquer sur le lien ci-dessous pour créer un nouveau mot de passe :
                    
                    [Lien de réinitialisation]
                    
                    Si vous n'avez pas demandé cette réinitialisation, veuillez ignorer cet email.
                    
                    Cordialement,
                    L'équipe de support
                """.trimIndent())
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Choisir une application email"))
            } else {
                Toast.makeText(
                    this,
                    "Aucune application email n'est installée",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Erreur lors de l'envoi de l'email: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}