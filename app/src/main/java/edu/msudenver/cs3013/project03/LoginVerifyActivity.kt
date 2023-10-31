package edu.msudenver.cs3013.project03

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import edu.msudenver.cs3013.project03.data.NameViewModel
import edu.msudenver.cs3013.project03.data.ViewModelFactory

class LoginVerifyActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_verify)

        //create viewmodel
        val model = ViewModelProvider(this, ViewModelFactory.getInstance())[NameViewModel::class.java]


        //get login info from viewmodel
        val firstName = model.getFirstName()
        val lastName = model.getLastName()
        val email = model.getEmail()

        model.firstName.observe({ this.lifecycle }) {
            Log.d("LoginActivity", "First Name: $it")
        }
        model.lastName.observe({ this.lifecycle }) {
            Log.d("LoginActivity", "Last Name: $it")
        }
        model.email.observe({ this.lifecycle }) {
            Log.d("LoginActivity", "Email: $it")
        }


        //display welcome message
        val nameToDisplay = firstName.plus(" ").plus(lastName)
        val greetingDisplay = findViewById<TextView>(R.id.greeting_display)
        greetingDisplay?.text = "${getString(R.string.welcome_to_the_app)} ${nameToDisplay}!"

        //go to main activity2 after 2 seconds
        Handler().postDelayed({
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }, 2000)
    }
}