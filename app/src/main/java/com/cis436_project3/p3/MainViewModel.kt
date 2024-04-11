package com.cis436_project3.p3

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

data class CatBreedDetails(
    val name: String,
    val description: String,
    val origin: String,
    val temperament: String
)
class MainViewModel : ViewModel() {
    private val API_KEY = "live_TYkc2OW8IzzAOje48nZWK67hcPwEHw7Qgrqye2Ott1IPekGIbrM5JrgnJXmcXAyf"
    private val _catBreeds = MutableLiveData<List<String>>()
    private val _catDetails = MutableLiveData<CatBreedDetails>()
    private val breedIdMap = mutableMapOf<String, String>()

    val catBreeds: LiveData<List<String>> get() = _catBreeds
    val catDetails: LiveData<CatBreedDetails> get() = _catDetails

    fun fetchCatBreeds(context: Context) {
        val url = "https://api.thecatapi.com/v1/breeds?api_key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            val breeds = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val name = jsonObject.getString("name")
                val id = jsonObject.getString("id")
                breeds.add(name)
                breedIdMap[name] = id
            }
            _catBreeds.value = breeds
        }, { error ->
            Log.e("MainViewModel", "Error fetching cat breeds: ${error.message}")
        })
        queue.add(request)
    }

    fun fetchCatDetails(context: Context, breedName: String) {
        val breedId = breedIdMap[breedName] ?: return
        val url = "https://api.thecatapi.com/v1/breeds/$breedId?api_key=$API_KEY"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET, url, { response ->
            val jsonObject = JSONObject(response)
            val details = CatBreedDetails(
                jsonObject.getString("name"),
                jsonObject.getString("description"),
                jsonObject.getString("origin"),
                jsonObject.getString("temperament")
            )
            _catDetails.value = details
        }, { error ->
            Log.e("MainViewModel", "Error fetching cat details: ${error.message}")
        })
        queue.add(request)
    }
}