package com.example.apphw13.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.apphw13.R
import com.example.apphw13.databinding.FragmentCreateAccountBinding
import com.example.apphw13.viewModels.CreateAccountViewModel

class createAccount : Fragment() {
    private val vModel : CreateAccountViewModel by activityViewModels()
    lateinit var binding : FragmentCreateAccountBinding
    val cardList = arrayListOf<CardView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.accountType_array,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner1.adapter = adapter
            binding.spinner2.adapter = adapter
            binding.spinner3.adapter = adapter
            binding.spinner4.adapter = adapter
            binding.spinner5.adapter = adapter
        }

    }

    private fun initViews() {
        cardList.addAll(listOf(binding.firstCard, binding.secondCard,binding.thirdCard,
                                binding.fourthCard, binding.fifthCard))
        var prefs = requireActivity().getSharedPreferences(resources.getString(R.string.app_name),
            AppCompatActivity.MODE_PRIVATE
        )
        var accountCount = prefs.getString(ACCOUNTCOUNT,"")
        var intAccountCount = Integer.parseInt(accountCount)

        for (i in 0 until intAccountCount){
            cardList[i].isVisible = true
        }

    }

}