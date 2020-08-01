package com.cartrack.users.data.remote

import androidx.lifecycle.MutableLiveData
import com.cartrack.users.data.remote.ApiInterface
import com.cartrack.users.data.remote.BaseDataSource
import com.google.gson.JsonElement
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteDataSource (val apiInterface: ApiInterface): BaseDataSource() {

    suspend fun getUsersList() = getResult { apiInterface.getUsersList() }

   /* fun getUsersList() : Observable<JsonElement> {
        return Observable.create { emitter ->
            apiInterface.getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    emitter.onNext(it)
                }, {
                    emitter.onError(it)
                    //it.printStackTrace()
                })

        }
    }*/
}