package kr.taehoon.baseapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable
import kr.taehoon.baseapplication.BR


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewDataBinding: T

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract val layoutResourceId: Int

    //Binding 객체를 얻은 후 수행되어야 할 작업을 위한 추상 메소드 ex) 리소스 초기화
    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Generic 과 layoutResourceId(추상 변수)를 활용하여 Binding 객체를 얻는다.
        viewDataBinding = DataBindingUtil.setContentView<T>(this, layoutResourceId).apply {
            setVariable(BR.activity, this@BaseActivity)
            lifecycleOwner = this@BaseActivity
        }
        initAfterBinding()
        setObserver()
    }

    /*
    * ViewModel의 LiveData들 Observer 초기화
    * RxAndroid, RxJava Observer 초기화
    * */
    abstract fun setObserver()

    /*
    * 모든 Observable의 구독 해제
    * 메모리독 누수방지
    * */
    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}