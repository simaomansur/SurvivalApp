package edu.msudenver.cs3013.project03

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import edu.msudenver.cs3013.project03.data.NameViewModel
import edu.msudenver.cs3013.project03.data.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.enter_button)?.setOnClickListener {

            val firstName = findViewById<TextInputEditText>(R.id.first_name)?.text.toString().trim()
            val lastName = findViewById<TextInputEditText>(R.id.last_name)?.text.toString().trim()
            val email = findViewById<TextInputEditText>(R.id.email)?.text.toString().trim()

            val model = ViewModelProvider(this, ViewModelFactory.getInstance())[NameViewModel::class.java]


            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {

                //update view model first and last name
                model.saveFirstName(firstName)
                model.saveLastName(lastName)
                model.saveEmail(email)

                //observe first and last name
                model.firstName.observe({ this.lifecycle }) {
                    Log.d("LoginActivity", "First Name: $it")
                }
                model.lastName.observe({ this.lifecycle }) {
                    Log.d("LoginActivity", "Last Name: $it")
                }
                model.email.observe({ this.lifecycle }) {
                    Log.d("LoginActivity", "Email: $it")
                }

                //go to login verify activity
                val intent = Intent(this, LoginVerifyActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, getString(R.string.please_enter_a_name), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        //shared preferences
        val sharedPref = getSharedPreferences("edu.msudenver.cs3013.project03", MODE_PRIVATE)
        val firstNameSP = sharedPref.getString("firstName", "")
        val lastNameSP = sharedPref.getString("lastName", "")
        val emailSP = sharedPref.getString("email", "")

        //set data in edit text
        findViewById<TextInputEditText>(R.id.first_name)?.setText(firstNameSP)
        findViewById<TextInputEditText>(R.id.last_name)?.setText(lastNameSP)
        findViewById<TextInputEditText>(R.id.email)?.setText(emailSP)
    }

    override fun onPause() {
        super.onPause()

        //shared preferences
        val sharedPref = getSharedPreferences("edu.msudenver.cs3013.project03", MODE_PRIVATE)
        val editor = sharedPref.edit()

        //get data from edit text
        val firstName = findViewById<TextInputEditText>(R.id.first_name)?.text.toString().trim()
        val lastName = findViewById<TextInputEditText>(R.id.last_name)?.text.toString().trim()
        val email = findViewById<TextInputEditText>(R.id.email)?.text.toString().trim()

        //save data to shared preferences
        editor.putString("firstName", firstName)
        editor.putString("lastName", lastName)
        editor.putString("email", email)
        editor.apply()
    }
}