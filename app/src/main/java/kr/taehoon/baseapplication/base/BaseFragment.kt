package kr.taehoon.baseapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import kr.taehoon.baseapplication.BR

abstract class BaseFragment<T: ViewDataBinding> : Fragment(){
    lateinit var viewDataBinding: T
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    abstract val layoutResId : Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResId , container, false)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewDataBinding.setVariable(BR.fragment,this)
        initOnCreatedView()
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnViewCreated()
    }

    override fun onStop() {
        compositeDisposable.dispose()
        super.onStop()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
    //onCreateView에서 실행되어야 할 작업
    abstract fun initOnCreatedView()
    //onViewCreated에서 실행되어야 할 작업
    abstract fun initOnViewCreated()

}