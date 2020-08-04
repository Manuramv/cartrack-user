package com.cartrack.users.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.model.CountryList
import com.cartrack.users.data.model.CountryListItem
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.ui.splash.UserAccountDetails
import com.cartrack.users.utils.JsonParser
import com.cartrack.users.utils.Resource
import com.cartrack.users.utils.ValidationUtils.isEmailValid
import com.cartrack.users.utils.ValidationUtils.isPwdValid
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) :ViewModel() {
    var emailAddress= MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var countryItem  = MutableLiveData<List<CountryListItem>>()
    var countryList  = arrayListOf<CountryListItem>()

    private val _id = MutableLiveData<Int>()
   // private val _user = loginRepository.getUser("user1", "password@18")
    private val _user = _id.switchMap { id ->
        loginRepository.getUser(emailAddress.value!!, password.value!!)!!
    }

    val user: LiveData<Resource<User>> = _user!!

   fun performLogin() {
       _id.value = _id.value?.plus(1);
   }

    val valid = MediatorLiveData<Boolean>().apply {
        var emailValid = false
        var pwdValid = false
        addSource(emailAddress) {
            emailValid = isEmailValid(it)
            value = emailValid && pwdValid

        }
        addSource(password) {
            pwdValid = isPwdValid(it)
            value = emailValid && pwdValid
        }
        value = emailValid && pwdValid
    }



    //get countrylist
    //Read the json from asset folder and update to database
    fun readCountryJson(context: Context){
        viewModelScope.launch {
            val jsonFileString = JsonParser.getJsonDataFromAsset(context, "CountryList.json")
            val gson = Gson()
            val listCountryType = object : TypeToken<CountryList>() {}.type
            var countrylist:CountryList = gson.fromJson(jsonFileString, listCountryType)
            countrylist.forEachIndexed {
                    idx, country -> Log.i("data", "> Item $idx:\n$country")
                countryList.add(country)
            }
            countryItem.value = countryList
        }
    }
}