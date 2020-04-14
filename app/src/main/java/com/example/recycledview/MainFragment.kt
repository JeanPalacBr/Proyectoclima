package com.example.recycledview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycledview.Util.getStringRequest
import com.example.recycledview.data.Cities
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), CitiesRecyclerViewAdapter.onListInteractions {


    val cities = mutableListOf<Cities>()
    private var adapter : CitiesRecyclerViewAdapter? = null
    lateinit var navController: NavController
    private lateinit var viewModel: ForecastViewModel
    private var userList2 = mutableListOf<Forecast>()
    val apik = "appid=37dd19dab504fd2b71578cb95bfa9bd8"
    val apidir = "https://api.openweathermap.org/data/2.5/"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        viewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        viewModel.addForecastCity(apidir+"weather?id=3689147&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3688689&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3687925&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3674962&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3688465&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3687238&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3668605&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3672486&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3675443&lang=es&units=metric&"+apik,2)
        viewModel.addForecastCity(apidir+"weather?id=3680656&lang=es&units=metric&"+apik,2)


            loadData2()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        adapter = CitiesRecyclerViewAdapter(cities,this)
        view.recyclerv.layoutManager = LinearLayoutManager(context)
        view.recyclerv.adapter = adapter

    }

    override fun onListItemInteraction(item: Cities?) {
        val bundle = bundleOf("data" to item,"cityname" to item!!.cityname)
        navController.navigate(R.id.action_mainFragment_to_personFragment, bundle)
        println("click " + item.cityname)
    }

      override fun onListButtonInteraction(item: Cities?) {

    }

     fun esta(valor: String): Boolean{
        var b = false
        for(i in 0 until cities.size){
            if(valor.equals(cities[i].cityname)){
                 b = true
            }

        }
        return b
    }
    fun loadData2() {
        viewModel.getCities().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                userList2 = obsUsers as MutableList<Forecast>
                var i: Int = 0
                for(cityx in userList2) {
                    if(esta(cityx.name)==false) {
                        val city = Cities(
                            cityx.name,
                            cityx.temp,
                            cityx.temp_max,
                            cityx.temp_min,
                            cityx.humidity,
                            cityx.feels_like,
                            cityx.pressure,
                            cityx.description,"http://api.openweathermap.org/img/w/"+cityx.icon+".png",
                            cityx.speed
                        )
                        println("cityy "+i+"  ... "+city)
                        cities.add(city)
                    }


                    i++
                }
                adapter!!.updateData()
            }
        })
    }






}