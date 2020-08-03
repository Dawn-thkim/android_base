package kr.taehoon.baseapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent


abstract class BaseViewModel : ViewModel(), KoinComponent {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    companion object {
    }
}