package com.example.apphw13.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.apphw13.R
import com.example.apphw13.databinding.FragmentCreateAccountBinding
import com.example.apphw13.models.Account
import com.example.apphw13.repository.AccountRepositpry
import com.example.apphw13.viewModels.CreateAccountViewModel

class createAccount : Fragment() {
    private val vModel : CreateAccountViewModel by activityViewModels()
    lateinit var binding : FragmentCreateAccountBinding
    lateinit var firstSpinnerItem : String
    lateinit var secondSpinnerItem : String
    lateinit var thirdSpinnerItem : String
    lateinit var fourthSpinnerItem : String
    lateinit var fifthSpinnerItem : String
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
        activity?.let { AccountRepositpry.initDB(it.applicationContext) }
        initViews()
        setSpinner()
        setListener()
    }

    private fun setListener() {
        binding.buttonRegister.setOnClickListener {
            saveInfo()
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

    fun setSpinner(){
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
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                firstSpinnerItem=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                secondSpinnerItem=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                thirdSpinnerItem=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                fourthSpinnerItem=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                fifthSpinnerItem=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun saveInfo() {
        val accountNumber1 = binding.accountNumber1.text.toString()
        val accountBalance1 = binding.balance1.text.toString()
        val account1 = Account(1, firstSpinnerItem, accountNumber1, accountBalance1)
        AccountRepositpry.insertAccount(account1)

        val accountNumber2 = binding.accountNumber2.text.toString()
        val accountBalance2 = binding.balance2.text.toString()
        val account2 = Account(2, secondSpinnerItem, accountNumber2, accountBalance2)
        AccountRepositpry.insertAccount(account2)

        val accountNumber3 = binding.accountNumber3.text.toString()
        val accountBalance3 = binding.balance3.text.toString()
        val account3 = Account(3, thirdSpinnerItem, accountNumber3, accountBalance3)
        AccountRepositpry.insertAccount(account3)

        val accountNumber4 = binding.accountNumber4.text.toString()
        val accountBalance4 = binding.balance4.text.toString()
        val account4 = Account(4, fourthSpinnerItem, accountNumber4, accountBalance4)
        AccountRepositpry.insertAccount(account4)

        val accountNumber5 = binding.accountNumber5.text.toString()
        val accountBalance5 = binding.balance5.text.toString()
        val account5 = Account(5, fifthSpinnerItem, accountNumber5, accountBalance5)
        AccountRepositpry.insertAccount(account5)

        Toast.makeText(activity, "اطلاعات با موفقیت ذخیره شد!" , Toast.LENGTH_SHORT ).show()

    }

}