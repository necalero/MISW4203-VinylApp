package com.vinyl.app

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.vinyl.app.databinding.ActivityMainBinding
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.volley.Response
import com.vinyl.app.brokers.VolleyBroker
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.vinyl.app.Artist
import com.vinyl.app.databinding.FragmentFirstBinding

class MainActivity : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)*/

        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)

        volleyBroker = VolleyBroker(this.applicationContext)

        val getButton: Button = findViewById(R.id.fetch_artists)
        val getResultTextView : TextView = findViewById(R.id.get_artists)
        val containerLayout: LinearLayout = findViewById(R.id.names_layout)
        getButton.setOnClickListener {
            volleyBroker.instance.add(VolleyBroker.getRequest("artists.json?key=780e6390",
                { response ->
                    try {
                        val jsonArray = JSONArray(response)
                        val artist = mutableListOf<Artist>()

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val id = jsonObject.getInt("id")
                            val nombre = jsonObject.getString("nombre")
                            val nacionalidad = jsonObject.getString("nacionalidad")
                            val biografia = jsonObject.getString("biografia")

                            artist.add(Artist(id, nombre, nacionalidad, biografia))

                            val button = Button(this)
                            button.text = nombre
                            button.setOnClickListener {
                                val intent = Intent(this, ArtistDetailActivity::class.java)
                                intent.putExtra("nombre", nombre)
                                intent.putExtra("nacionalidad", nacionalidad)
                                intent.putExtra("biografia", biografia)
                                startActivity(intent)
                            }
                            containerLayout.addView(button)

                        }

                        /*// Mostrar solo los nombres de los artistas en el TextView
                        val nombres = artist.map { it.nombre }
                        val nacionalidades = artist.map { it.nacionalidad }
                        val biografias = artist.map { it.biografia }
                        //getResultTextView.text = nombres.joinToString("\n")
                        for (nombre in nombres) {

                        }*/
                    } catch (e: JSONException) {
                        Log.e("TAG", "Error parsing JSON: ${e.message}", e)
                        getResultTextView.text = "Error parsing JSON: ${e.message}"
                    }
                },
                {
                    Log.d("TAG", it.toString())
                    getResultTextView.text = "That didn't work!"
                }
            ))
        }




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}