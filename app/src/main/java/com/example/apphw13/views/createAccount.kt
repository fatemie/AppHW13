package com.example.apphw13.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import com.example.apphw13.R
import com.example.apphw13.databinding.FragmentCreateAccountBinding
import com.example.apphw13.models.Account
import com.example.apphw13.repository.AccountRepositpry
import com.example.apphw13.viewModels.MainViewModel

class createAccount : Fragment() {
    private val vModel : MainViewModel by activityViewModels()
    lateinit var binding : FragmentCreateAccountBinding
    var firstSpinnerItem = ""
    var secondSpinnerItem = ""
    var thirdSpinnerItem = ""
    var fourthSpinnerItem = ""
    var fifthSpinnerItem = ""
    val cardList = arrayListOf<CardView>()
    var intAccountCount = 0
    var accountNumberEditTextList = arrayListOf<EditText>()
    var accountBalanceEditTextList = arrayListOf<EditText>()

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
            if(infoCorrected()) {
                saveInfo()
            }
        }
    }

    private fun initViews() {
        var prefs = requireActivity().getSharedPreferences(resources.getString(R.string.app_name),
            AppCompatActivity.MODE_PRIVATE)
        var accountCount = prefs.getString(ACCOUNTCOUNT,"")
        intAccountCount = Integer.parseInt(accountCount)

        cardList.addAll(listOf(binding.firstCard, binding.secondCard,binding.thirdCard,
                                binding.fourthCard, binding.fifthCard))

        accountNumberEditTextList = arrayListOf<EditText>(binding.accountNumber1,
            binding.accountNumber2,binding.accountNumber3,binding.accountNumber4, binding.accountNumber5)

        accountBalanceEditTextList = arrayListOf<EditText>(binding.balance1,
            binding.balance2, binding.balance3, binding.balance4, binding.balance5)

        vModel.initViews(cardList, intAccountCount)
    }

    fun setSpinner(){
        val spinnerArray = arrayListOf<Spinner>(binding.spinner1, binding.spinner2, binding.spinner3
                                                ,binding.spinner4, binding.spinner5)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.accountType_array,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            for (spinner in spinnerArray)
            spinner.adapter = adapter
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
        val itemList = arrayListOf(firstSpinnerItem,secondSpinnerItem,thirdSpinnerItem,
        fourthSpinnerItem,fifthSpinnerItem)
        val accountNumberEditTextList = arrayListOf<EditText>(binding.accountNumber1,
        binding.accountNumber2,binding.accountNumber3,binding.accountNumber4, binding.accountNumber5)
        val accountBalanceEditTextList = arrayListOf<EditText>(binding.balance1,
        binding.balance2, binding.balance3, binding.balance4, binding.balance5)
        for(i in 0 .. intAccountCount -1){
            Log.e("createAccountInfo", itemList[i])
            var account = Account(0, itemList[i], accountNumberEditTextList[i].text.toString(),
            accountBalanceEditTextList[i].text.toString())
            AccountRepositpry.insertAccount(account)
        }
        Toast.makeText(activity, "اطلاعات با موفقیت ذخیره شد!" , Toast.LENGTH_SHORT ).show()
    }

    fun infoCorrected():Boolean {
        var flag = true
        for (i in 0..intAccountCount - 1) {
            if (accountNumberEditTextList[i].text.isNullOrEmpty() || accountNumberEditTextList[i].text.length != 3) {
                accountNumberEditTextList[i].setError("این فیلد را صحیح تکمیل کنید!")
                flag = false
            }
            if(accountBalanceEditTextList[i].text.isNullOrEmpty()){
                accountBalanceEditTextList[i].setError("این فیلد را تکمیل کنید!")
                flag = false
            }
        }
        return flag
    }

}