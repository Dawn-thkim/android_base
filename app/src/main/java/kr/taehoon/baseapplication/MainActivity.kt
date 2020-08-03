package kr.taehoon.baseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.taehoon.baseapplication.base.BaseActivity
import kr.taehoon.baseapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun setObserver() {
        TODO("Not yet implemented")
    }

}