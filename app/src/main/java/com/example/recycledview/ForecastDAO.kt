package com.example.recycledview


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class ForecastDAO private constructor(var context: Context) {

    private val forecasts = MutableLiveData<List<Forecast>>()
    private val cities = MutableLiveData<List<Forecast>>()
    private val foreList = mutableListOf<Forecast>()
    private val cityList = mutableListOf<Forecast>()
    private var queue: RequestQueue


    init{
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object{
        @Volatile
        private var INSTANCE: ForecastDAO? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: ForecastDAO(context).also { INSTANCE = it }
            }
    }

    fun addForecasts(url:String, type:Int) {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject(url,type))

    }

    internal fun getForecasts() = forecasts
    internal fun getCities() = cities

    fun getJsonObject(url: String, type:Int): JsonObjectRequest{
        //val url ="https://api.openweathermap.org/data/2.5/forecast?id=3689147&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8"
        //"https://randomuser.me/api/?results=5"
            //"http://dataservice.accuweather.com/forecasts/v1/daily/5day/107123"
              //
        val rr : String = ""
        val JOR = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                println("bien "+type)
                parseObjectG(response, type)

            },
            Response.ErrorListener { error->
                Log.d("WebRequestTest", "That didn't work ${error.message}")
            }
        )
        return JOR
    }

    private fun parseObjectG(response: JSONObject, type: Int) {
        val list = Forecast.getUser(response,type)
        println(response)
        val i: Int = 0
        val size: Int = list.size
        if(type==1){
            for (i in 0 until size) {
                val fore = list[i]
                foreList.add(fore)
            }

            forecasts.value = foreList
        }else{
            for (i in 0 until size) {
                val cit = list[i]
                cityList.add(cit)
            }
            cities.value = cityList

        }

    }

}