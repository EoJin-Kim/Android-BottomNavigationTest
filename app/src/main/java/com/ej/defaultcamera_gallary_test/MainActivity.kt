package com.ej.defaultcamera_gallary_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ej.defaultcamera_gallary_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val firstFragment = BlankFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        val transition = supportFragmentManager.beginTransaction()
        val fragment = BlankFragment.newInstance()
        transition.replace(R.id.fragmentContainerView, fragment)
        transition.commit()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onStart() {
        super.onStart()
        initNavigationBar()
    }

    private fun initNavigationBar() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_wallet -> {
                        changeFragment(firstFragment)
                    }
                    R.id.action_transaction -> {
                        changeFragment(SecondFragment.newInstance())
                    }
                    R.id.action_event -> {
                        changeFragment(ThirdFragment.newInstance())
                    }
                    R.id.action_account -> {
                        changeFragment(FourthFragment.newInstance())
                    }

                }
                true
            }
            selectedItemId = R.id.action_wallet
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
}