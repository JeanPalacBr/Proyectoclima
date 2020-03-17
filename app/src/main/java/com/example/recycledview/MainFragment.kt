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
import com.example.recycledview.data.User
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), MyUserRecyclerViewAdapter.onListInteractions {

    val users = mutableListOf<User>()
    val cities = mutableListOf<Cities>()
    private var adapter : MyUserRecyclerViewAdapter? = null
    lateinit var navController: NavController
    private lateinit var viewModel: RandomUserViewModel
    private var userList = mutableListOf<RandomUser>()
    private var userList2 = mutableListOf<RandomUser>()
    val urls = mutableListOf<String>()
    val urls2 = mutableListOf<String>()
    //lateinit var binderP : FragmentPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        //binderP = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container, false)
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3689147&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Barranquilla
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3688689&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Bogota
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3687925&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Cali
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3674962&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Medellin
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3688465&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Bucaramanga
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3687238&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Cartagena
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3672486&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Pereira
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3668605&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Santa marta
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3675443&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Manizales
        urls.add("https://api.openweathermap.org/data/2.5/forecast?id=3680656&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Ibague

        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3689147&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Barranquilla
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3688689&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Bogota
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3687925&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Cali
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3674962&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Medellin
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3688465&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Bucaramanga
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3687238&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Cartagena
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3672486&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Pereira
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3668605&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Santa marta
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3675443&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Manizales
        urls2.add("https://api.openweathermap.org/data/2.5/weather?id=3680656&lang=es&units=metric&mode=JSON&appid=37dd19dab504fd2b71578cb95bfa9bd8") //Ibague

        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(getStringRequest(urls[0]))
        for (i in 0 .. urls.size-1) {
            viewModel.addUsers(urls[i],1)

            loadData()
        }
        VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(getStringRequest(urls2[0]))
        for (i in 0 .. urls2.size-1) {
            viewModel.addUsers(urls2[i],2)
            loadData2()
        }
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
        val bundle = bundleOf("data" to item,"name" to item!!.cityname)
        navController!!.navigate(R.id.action_mainFragment_to_personFragment, bundle)
        println("click " + item!!.cityname)
    }

      override fun onListButtonInteraction(item: Cities?) {

    }

    fun loadData2() {
        viewModel.getCities().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                userList2 = obsUsers as MutableList<RandomUser>
                var i: Int = 0
                for(randUser in userList2) {
                    var city = Cities(
                        randUser.name,
                        randUser.temp,
                        randUser.temp_max,
                        randUser.temp_min,
                        randUser.humidity,
                        randUser.feels_like,
                        randUser.pressure,
                        randUser.description,
                        randUser.icon,
                        randUser.speed
                    )

                    println("cityy "+i+"  ... "+city)
                    cities.add(city)
                    i++
                }
                adapter!!.updateData()
            }
        })
    }




    fun loadData() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                userList = obsUsers as MutableList<RandomUser>
                    var i = 0
                    for (randUser in userList) {
                            var user = User(
                                randUser.name, randUser.temp, randUser.temp_max, randUser.temp_min,
                                randUser.humidity, randUser.feels_like, randUser.pressure,
                                randUser.description, randUser.icon, randUser.speed, randUser.dt_txt
                            )
                            println("userr  "+i+"  ... "+user)
                            users.add(user)
                        i++
                    }
                adapter!!.updateData()

            }
        })
    }

}