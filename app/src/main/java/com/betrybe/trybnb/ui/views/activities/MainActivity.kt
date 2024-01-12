package com.betrybe.trybnb.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.ActivityMainBinding
import com.betrybe.trybnb.ui.views.fragments.CreateReservationFragment
import com.betrybe.trybnb.ui.views.fragments.ProfileFragment
import com.betrybe.trybnb.ui.views.fragments.ReservationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.navigationBottomView.selectedItemId = R.id.profile_menu_tem

        binding.navigationBottomView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.reservation_menu_item -> {
                    switchFragment(ReservationFragment())
                    true
                }
                R.id.create_reservation_menu_item -> {
                    switchFragment(CreateReservationFragment())
                    true
                }
                R.id.profile_menu_tem -> {
                    switchFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun switchFragment(newFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragmentContainer.id, newFragment)
            .commit()
    }
}
