package com.example.recycledview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycledview.Util.getStringRequest
import com.example.recycledview.data.Cities
import com.example.recycledview.data.Forecasts
import com.example.recycledview.databinding.FragmentForecastBinding
import kotlinx.android.synthetic.main.fragment_forecast.view.*


/**
 * A simple [Fragment] subclass.
 */
class ForecastFragment : Fragment(), ForecastsRecyclerViewAdapter2.onListInteractions {
    val apik = "appid=37dd19dab504fd2b71578cb95bfa9bd8"
    val apidir = "https://api.openweathermap.org/data/2.5/"
    val users = mutableListOf<Forecasts>()
    private var userList = mutableListOf<Forecast>()
    private var adapter : ForecastsRecyclerViewAdapter2? = null
    lateinit var navController: NavController
    private lateinit var viewModel: ForecastViewModel
    override fun onListItemInteraction(item: Forecasts?) {
        Toast.makeText(this.context, "Cargado", Toast.LENGTH_LONG).show()
    }

    override fun onListButtonInteraction(item: Forecasts?) {

    }

    lateinit var user : Cities
    lateinit var binder : FragmentForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_forecast,container,false)
        viewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = arguments?.getParcelable("data")!!
        navController = Navigation.findNavController(view)
        adapter = ForecastsRecyclerViewAdapter2(users,this)
        view.recyclerva.layoutManager = LinearLayoutManager(context)
        view.recyclerva.adapter = adapter


        VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(
            getStringRequest(apidir+"forecast?id=3689147&lang=es&units=metric&"+apik))


        when(user.cityname){
            "Barranquilla"->viewModel.addForecastCity(apidir+"forecast?id=3689147&lang=es&units=metric&"+apik,1)
            "Bogotá"->viewModel.addForecastCity(apidir+"forecast?id=3688689&lang=es&units=metric&"+apik,1)
            "Santiago de Cali"->viewModel.addForecastCity(apidir+"forecast?id=3687925&lang=es&units=metric&"+apik,1)
            "Medellín"->viewModel.addForecastCity(apidir+"forecast?id=3674962&lang=es&units=metric&"+apik,1)
            "Bucaramanga"->viewModel.addForecastCity(apidir+"forecast?id=3688465&lang=es&units=metric&"+apik,1)
            "Cartagena"->viewModel.addForecastCity(apidir+"forecast?id=3687238&lang=es&units=metric&"+apik,1)
            "Pereira"->viewModel.addForecastCity(apidir+"forecast?id=3672486&lang=es&units=metric&"+apik,1)
            "Santa Marta"->viewModel.addForecastCity(apidir+"forecast?id=3668605&lang=es&units=metric&"+apik,1)
            "Manizales"->viewModel.addForecastCity(apidir+"forecast?id=3675443&lang=es&units=metric&"+apik,1)
            "Ibagué"->viewModel.addForecastCity(apidir+"forecast?id=3680656&lang=es&units=metric&"+apik,1)
        }

        loadData()


        //binder = DataBindingUtil.setContentView(this.requireActivity(), R.layout.fragment_forecast)
        binder.user2 = user

        Toast.makeText(this.context, "Perfil cargado", Toast.LENGTH_LONG).show()
    }
    fun ourhour(datatime: String ):String{
            var hora : Int = datatime.substring(11,13).toInt()
            var dia  : Int = datatime.substring(8,10).toInt()
            var mes : Int = datatime.substring(5,7).toInt()
            var anno: Int = datatime.substring(0,4).toInt()
        var auxdata : String =""
            if(hora >=5){
                hora = hora - 5

            }else{
                hora = 24 - (5-hora)

                dia = dia - 1
                if((dia+1)<2){

                    mes--
                    if(mes%2==0){
                        if(mes== 2){
                            dia = 28
                        }else{
                            dia = 30
                        }
                    }else{
                        dia = 31
                    }
                    if(mes<1){

                        mes--
                    }else{
                        mes = 12
                        anno--
                    }
                }

            }
            auxdata = anno.toString()+"-"+mes.toString()+"-"+dia.toString()+" "+hora.toString()+datatime.substring(13)
            return auxdata

    }
    fun loadData() {

        viewModel.getForecasts().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                userList = obsUsers as MutableList<Forecast>
                var i = 0

                for (randUser in userList) {
                    val ourdata: String = ourhour(randUser.dt_txt)
                    val user = Forecasts(
                        randUser.name, randUser.temp, randUser.temp_max, randUser.temp_min,
                        randUser.humidity, randUser.feels_like, randUser.pressure,
                        randUser.description,"http://api.openweathermap.org/img/w/"+randUser.icon+".png",
                        randUser.speed, ourdata
                    )
                    println("userra  "+i+"  ... "+user)
                    users.add(user)
                    i++
                }
                adapter!!.updateData()

            }
        })
    }


}