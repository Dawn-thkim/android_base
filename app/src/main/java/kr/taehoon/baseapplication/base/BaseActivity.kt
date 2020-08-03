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

    abstract fun initAfterBinding()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView<T>(this, layoutResourceId).apply{
            setVariable(BR.activity, this@BaseActivity)
            lifecycleOwner = this@BaseActivity
        }
        initAfterBinding()
    }

    override fun onResume() {
        setObserver()
        super.onResume()
    }


    abstract fun setObserver()
    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}