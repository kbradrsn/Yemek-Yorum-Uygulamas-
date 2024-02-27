package com.example.my

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val guncelKullanici = auth.currentUser
        if (guncelKullanici != null) {
            val intent = Intent(this, com.example.my.HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }

        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.PasswordText)
    }

    fun kayitol(view: View) {
        val email = emailText.text.toString()
        val sifre = passwordText.text.toString()

        auth.createUserWithEmailAndPassword(email, sifre).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, com.example.my.HaberlerActivity::class.java)
                startActivity(intent)
                finish()
            } else {

                Toast.makeText(applicationContext, "Kayıt başarısız: ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun girisyap(view: View) {
        auth.signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val guncelKullanici = auth.currentUser?.email.toString()
                Toast.makeText(this, "Hoşgeldin: ${guncelKullanici}", Toast.LENGTH_LONG).show()

                val intent = Intent(this, com.example.my.HaberlerActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }
}
