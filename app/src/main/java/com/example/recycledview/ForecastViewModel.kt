package com.example.recycledview


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class RandomUserViewModel(application: Application) : AndroidViewModel(application) {

    private var forecastDAO : ForecastDAO

    init {
        forecastDAO = ForecastDAO.getInstance(this.getApplication())
    }

    fun addUsers(url:String, type:Int) {
        forecastDAO.addUsers(url,type)
    }

    internal fun getUsers(): MutableLiveData<List<Forecast>> {
        return forecastDAO.getUsers()
    }
    internal fun getCities(): MutableLiveData<List<Forecast>> {
        return forecastDAO.getCities()
    }

}