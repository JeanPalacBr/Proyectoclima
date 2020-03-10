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
import com.example.recycledview.data.User
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), MyUserRecyclerViewAdapter.onListInteractions {

    val users = mutableListOf<User>()
    private var adapter : MyUserRecyclerViewAdapter? = null
    lateinit var navController: NavController
    private lateinit var viewModel: RandomUserViewModel
    private var userList = mutableListOf<RandomUser>()
    //lateinit var binderP : FragmentPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        //binderP = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container, false)

        /*users.add(User("Jean", "Palacio",50,"Trabajando","https://randomuser.me/api/portraits/men/67.jpg"))
        users.add(User("Nino", "Mercado",12,"Trabajando","https://randomuser.me/api/portraits/men/3.jpg"))
        users.add(User("Juan", "Martinez",13,"Trabajando", "https://randomuser.me/api/portraits/men/45.jpg"))
        users.add(User("Jorge", "Ramirez",18,"Trabajando", "https://randomuser.me/api/portraits/men/50.jpg"))
        users.add(User("Jesus", "Ibañez",21,"Trabajando","https://randomuser.me/api/portraits/men/45.jpg"))
        users.add(User("Ivan", "Castañeda",24,"Estudiando", "https://randomuser.me/api/portraits/men/46.jpg"))
        users.add(User("Ramon", "Segura",28,"Estudiando","https://randomuser.me/api/portraits/men/47.jpg"))
        users.add(User("Ruben", "Agudelo", 58,"Estudiando","https://randomuser.me/api/portraits/men/48.jpg"))
        users.add(User("Teofilo", "Gutierrez",16,"Estudiando", "https://randomuser.me/api/portraits/men/49.jpg"))
        users.add(User("Gabriel", "Fuentes", 35,"Estudiando","https://randomuser.me/api/portraits/men/51.jpg"))
        users.add(User("Rodolfo", "Arruabarrena",40,"Estudiando", "https://randomuser.me/api/portraits/men/52.jpg"))
        users.add(User("Romario", "Da silva",70,"Estudiando","https://randomuser.me/api/portraits/men/53.jpg"))
        users.add(User("Marcelo", "Bielsa",80,"Estudiando","https://randomuser.me/api/portraits/men/54.jpg"))
        users.add(User("Francisco", "Maturana",90,"Estudiando", "https://randomuser.me/api/portraits/men/55.jpg"))
        users.add(User("Jairo", "Patiño",44,"Estudiando", "https://randomuser.me/api/portraits/men/56.jpg"))
        users.add(User("Zlatan", "Osorio",14,"Emprendiendo", "https://randomuser.me/api/portraits/men/57.jpg"))
        users.add(User("Marco", "Di Matteo",87,"Emprendiendo", "https://randomuser.me/api/portraits/men/58.jpg"))
        users.add(User("Luca", "Toni",28,"Emprendiendo", "https://randomuser.me/api/portraits/men/59.jpg"))
        users.add(User("Romulo", "Giaccherini",32,"Emprendiendo","https://randomuser.me/api/portraits/men/61.jpg"))
        users.add(User("Paolo", "Maldini",35,"Emprendiendo", "https://randomuser.me/api/portraits/men/62.jpg")) */

        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        viewModel.addUsers()

        VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(getStringRequest())

        loadData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        adapter = MyUserRecyclerViewAdapter(users,this)
        view.recyclerv.layoutManager = LinearLayoutManager(context)
        view.recyclerv.adapter = adapter

    }

    override fun onListItemInteraction(item: User?) {
        val bundle = bundleOf("data" to item,"name" to item!!.name)
        navController!!.navigate(R.id.action_mainFragment_to_personFragment, bundle)
        println("click " + item!!.name)
    }

      override fun onListButtonInteraction(item: User?) {

    }
    fun loadData() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                userList = obsUsers as MutableList<RandomUser>

                for(randUser in userList) {
                    var user = User(
                        randUser.name.first, randUser.name.last,
                        randUser.email, randUser.phone,randUser.picture.large
                    )
                    users.add(user)
                }
                adapter!!.updateData()
            }
        })
    }

}