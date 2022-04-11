package com.example.apphw13.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apphw13.R
import com.example.apphw13.databinding.FragmentShowAccountBinding
import com.example.apphw13.repository.AccountRepositpry

class showAccount : Fragment() {
    lateinit var binding : FragmentShowAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
    }

    private fun initviews() {
        val account = AccountRepositpry.getAccount(1)
        binding.typeAccount1.text = account?.type
        binding.accountNumber1.text = account?.number
        binding.balance1.text = account?.balance
    }


}