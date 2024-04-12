package com.cis436_project3.p3

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainViewModel : ViewModel(){//end class

    private val API_KEY = "live_TYkc2OW8IzzAOje48nZWK67hcPwEHw7Qgrqye2Ott1IPekGIbrM5JrgnJXmcXAyf"
    private val _catBreeds = MutableLiveData<List<String>>()
    val catBreeds: MutableLiveData<List<String>>
        get() = _catBreeds

    fun fetchCatBreeds(context: Context){
        val catURL = "https://api.thecatapi.com/v1/breeds?api_key=$API_KEY"
        val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET, catURL,
            { response ->
                val catsArray = JSONArray(response)
                val breeds = mutableListOf<String>()

                for(i in 0 until catsArray.length()) {

                    val theCat : JSONObject = catsArray.getJSONObject(i)
                    breeds.add(theCat.getString("name"))
//                    Log.i("MainViewModel", "Cat Breed: ${theCat.getString("name")}")
                }
                _catBreeds.value = breeds
            }, {
                Log.i("MainViewModel", "Cat Request Failed")
            })
        queue.add(stringRequest)
    }


}