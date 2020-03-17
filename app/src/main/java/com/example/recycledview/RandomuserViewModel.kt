package com.example.recycledview


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class RandomUserViewModel(application: Application) : AndroidViewModel(application) {

    private var randomUserDao : RandomUserDao

    init {
        randomUserDao = RandomUserDao.getInstance(this.getApplication())
    }

    fun addUsers(url:String, type:Int) {
        randomUserDao.addUsers(url,type)
    }

    internal fun getUsers(): MutableLiveData<List<RandomUser>> {
        return randomUserDao.getUsers()
    }
    internal fun getCities(): MutableLiveData<List<RandomUser>> {
        return randomUserDao.getCities()
    }

}