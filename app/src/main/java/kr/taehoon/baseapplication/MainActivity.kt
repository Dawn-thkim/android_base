package kr.taehoon.baseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.taehoon.baseapplication.base.BaseActivity
import kr.taehoon.baseapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    //Binding 객체를 얻은 후 수행되어야 할 작업을 위한 추상 메소드 ex) 리소스 초기화
    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    //ViewModel의 LiveData들 Observer 선언 함수
    override fun setObserver() {
        TODO("Not yet implemented")
    }

}