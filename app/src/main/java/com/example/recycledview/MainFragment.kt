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
class MainFragment : Fragment(), MyUserRecyclerViewAdapter.onListInteractions {


    val cities = mutableListOf<Cities>()
    private var adapter : MyUserRecyclerViewAdapter? = null
    lateinit var navController: NavController
    private lateinit var viewModel: RandomUserViewModel
    private var userList2 = mutableListOf<Forecast>()
    val urls2 = mutableListOf<String>()
    val apik = "appid=37dd19dab504fd2b71578cb95bfa9bd8"
    val apidir = "https://api.openweathermap.org/data/2.5/"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        //binderP = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container, false)

        urls2.add(apidir+"weather?id=3689147&lang=es&units=metric&"+apik) //Barranquilla
        urls2.add(apidir+"weather?id=3688689&lang=es&units=metric&"+apik) //Bogota
        urls2.add(apidir+"weather?id=3687925&lang=es&units=metric&"+apik) //Cali
        urls2.add(apidir+"weather?id=3674962&lang=es&units=metric&"+apik) //Medellin
        urls2.add(apidir+"weather?id=3688465&lang=es&units=metric&"+apik) //Bucaramanga
        urls2.add(apidir+"weather?id=3687238&lang=es&units=metric&"+apik) //Cartagena
        urls2.add(apidir+"weather?id=3672486&lang=es&units=metric&"+apik) //Pereira
        urls2.add(apidir+"weather?id=3668605&lang=es&units=metric&"+apik) //Santa marta
        urls2.add(apidir+"weather?id=3675443&lang=es&units=metric&"+apik) //Manizales
        urls2.add(apidir+"weather?id=3680656&lang=es&units=metric&"+apik) //Ibague

        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(getStringRequest(urls2[0]))

        viewModel.addUsers(urls2[0],2)
        viewModel.addUsers(urls2[1],2)
        viewModel.addUsers(urls2[2],2)
        viewModel.addUsers(urls2[3],2)
        viewModel.addUsers(urls2[4],2)
        viewModel.addUsers(urls2[5],2)
        viewModel.addUsers(urls2[6],2)
        viewModel.addUsers(urls2[7],2)
        viewModel.addUsers(urls2[8],2)
        viewModel.addUsers(urls2[9],2)


            loadData2()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        adapter = MyUserRecyclerViewAdapter(cities,this)
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