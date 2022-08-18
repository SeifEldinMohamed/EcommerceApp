package com.seif.ecommerceapp.ui.introfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.seif.ecommerceapp.R
import com.seif.ecommerceapp.databinding.ActivityStartBinding
import com.seif.ecommerceapp.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    private lateinit var binding : FragmentIntroBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_intro, container, false)
    }


}