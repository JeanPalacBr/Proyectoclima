package com.example.recycledview


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class RandomUserDao private constructor(var context: Context) {

    private val users = MutableLiveData<List<RandomUser>>()
    private val cities = MutableLiveData<List<RandomUser>>()
    private val userList = mutableListOf<RandomUser>()
    private val cityList = mutableListOf<RandomUser>()
    private var queue: RequestQueue


    init{
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object{
        @Volatile
        private var INSTANCE: RandomUserDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: RandomUserDao(context).also { INSTANCE = it }
            }
    }

    fun addUsers(url:String, type:Int) {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject(url,type))

    }

    internal fun getUsers() = users
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
        val list = RandomUser.getUser(response,type)
        println(response)
        val i: Int = 0
        val size: Int = list.size
        if(type==1){
            for (i in 0 until size) {
                val user = list[i]
                userList.add(user)
            }

            users.value = userList
        }else{
            for (i in 0 until size) {
                val user = list[i]
                cityList.add(user)
            }
            cities.value = cityList

        }

    }

}