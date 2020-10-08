package com.peter.dovememo.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import com.peter.dovememo.R
import com.peter.dovememo.databinding.ActivityMainBinding
import com.peter.dovememo.main.fragment.ListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    private val fragmentList: List<Fragment> =
        listOf(
            //androidx.fragment.app.ListFragment를 임포드했음
            ListFragment()
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        setObserver()
    }
    //프레그 라이브데이터를 바라보겠다. 변경되면 바로 입력되는것
    private fun setObserver() {
        viewModel.fragLiveData.observe(this, Observer { frag ->
            val fragManager = supportFragmentManager
            val fragTransaction = fragManager.beginTransaction()
            fragTransaction.replace(
                binding.frameLayout.id,
                fragmentList[frag.position]
            ).commit()

        })
    }
}