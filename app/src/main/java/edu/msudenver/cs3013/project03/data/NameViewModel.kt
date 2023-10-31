package edu.msudenver.cs3013.project03.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel: ViewModel() {
    //create mutable live data for first name and last name
    private val _firstName = MutableLiveData<String>()
    private val _lastName = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()

    //create live data for first name and last name
    val firstName: LiveData<String> = _firstName
    val lastName: LiveData<String> = _lastName
    val email: LiveData<String> = _email

    init {
        _firstName.postValue("")
        _lastName.postValue("")
        _email.postValue("")
    }

    //save first name and last name
    fun saveFirstName(firstName: String) {
        _firstName.postValue(firstName)
    }

    fun saveLastName(lastName: String) {
        _lastName.postValue(lastName)
    }

    //save email
    fun saveEmail(email: String) {
        _email.postValue(email)
    }

    //get first name and last name
    fun getFirstName(): String {
        return firstName.value.toString()
    }

    fun getLastName(): String {
        return lastName.value.toString()
    }

    //get email
    fun getEmail(): String {
        return email.value.toString()
    }

    companion object {
        private var instance : NameViewModel? = null
        fun getInstance() =
            instance?: synchronized(NameViewModel::class.java){
                instance?: NameViewModel().also { instance = it }
            }
    }

}