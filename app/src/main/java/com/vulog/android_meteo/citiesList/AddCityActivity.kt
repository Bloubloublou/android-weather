package com.vulog.android_meteo.citiesList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.vulog.android_meteo.UserPrefs
import android.widget.TextView
import android.content.Context
import android.content.Intent
import android.widget.ImageButton
import com.vulog.android_meteo.R

/**
 * This activity allows the user to search for a city. When the user clicks on the search button, we go
 * back to the city list acitivity ; if the city is found it is displayed, otherwise it won't show.
 */
class AddCityActivity : AppCompatActivity() {

    private lateinit var cancelButton: TextView
    private lateinit var searchBar: EditText
    private lateinit var searchBtn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        cancelButton = findViewById(R.id.cancel_btn)
        searchBar = findViewById(R.id.search_bar)
        searchBtn = findViewById(R.id.search_btn)

        cancelButton.setOnClickListener(View.OnClickListener {
            goToListActivity()
        })

        searchBtn.setOnClickListener(View.OnClickListener {
            UserPrefs.getInstance(this).addCity(searchBar.text.toString())
            goToListActivity()
        })
    }

    fun goToListActivity() {
        val intent = ListOfCitiesActivity.newIntent(this)
        startActivity(intent)
    }
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddCityActivity::class.java)
        }
    }
}
