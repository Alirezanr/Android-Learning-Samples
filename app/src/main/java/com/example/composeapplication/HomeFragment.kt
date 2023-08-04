package com.example.composeapplication

import android.os.Bundle
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(),
    OnBackPressedDelegation by OnBackPressedDelegationImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerOnBackPressedDelegation(activity, this.lifecycle) {
            // OnBackPressed fired!!!
        }
    }
}