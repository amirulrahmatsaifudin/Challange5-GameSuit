package com.example.gamesuit.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.gamesuit.R
import com.example.gamesuit.onboarding.AdapterOnboarding
import com.example.gamesuit.onboarding.OnBoarding1Fragment
import com.example.gamesuit.onboarding.OnBoarding2Fragment
import com.example.gamesuit.onboarding.OnBoarding3Fragment
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val viewPager: ViewPager2 = findViewById(R.id.viewpager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            OnBoarding1Fragment(),
            OnBoarding2Fragment(),
            OnBoarding3Fragment()
        )

        val adapter = AdapterOnboarding(fragments, this)
        viewPager.adapter = adapter
        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        wormDotsIndicator.setViewPager2(viewPager)

    }
}