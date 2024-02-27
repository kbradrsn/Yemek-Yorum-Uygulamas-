package com.example.my

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.my.ui.theme.MyTheme
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.material3.MaterialTheme

class HaberlerActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.haberler)

        auth = FirebaseAuth.getInstance()
    }

    fun cikisyap(view: View) {
        auth.signOut()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun foto_paylas(view: View) {
        val intent = Intent(this, FotografPaylasmaAktivite::class.java)
        startActivity(intent)
    }
}
