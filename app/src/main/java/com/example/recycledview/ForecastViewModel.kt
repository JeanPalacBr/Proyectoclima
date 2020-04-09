package com.example.recycledview


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ForecastViewModel(application: Application) : AndroidViewModel(application) {

    private var forecastDAO : ForecastDAO

    init {
        forecastDAO = ForecastDAO.getInstance(this.getApplication())
    }

    fun addForecastCity(url:String, type:Int) {
        forecastDAO.addForecasts(url,type)
    }

    internal fun getForecasts(): MutableLiveData<List<Forecast>> {
        return forecastDAO.getForecasts()
    }
    internal fun getCities(): MutableLiveData<List<Forecast>> {
        return forecastDAO.getCities()
    }

}