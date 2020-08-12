package kr.taehoon.baseapplication

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kr.taehoon.baseapplication.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    fun getAppProfile(test: String) {
        apiService.getAppProfile(test)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Test Success", "$it")
            }, {
                Log.e("Test Fail", "${it.message}")
            }).addTo(compositeDisposable)
    }
}