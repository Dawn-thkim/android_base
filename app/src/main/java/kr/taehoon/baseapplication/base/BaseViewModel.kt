package kr.taehoon.baseapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import kr.taehoon.baseapplication.util.ApiService
import org.koin.core.KoinComponent
import org.koin.core.inject


abstract class BaseViewModel : ViewModel(), KoinComponent {
    val apiService : ApiService by inject()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    companion object {
    }
}