package com.example.apphw13.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.apphw13.R
import com.example.apphw13.databinding.FragmentSelectAccountBinding
import com.example.apphw13.models.Account
import com.example.apphw13.repository.AccountRepositpry
import com.example.apphw13.viewModels.MainViewModel

class selectAccount : Fragment() {
    lateinit var binding : FragmentSelectAccountBinding
    private val vModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListener()
    }

    fun initViews(){
        vModel.accountFirst()
        //Toast.makeText(activity, "${vModel.minId}", Toast.LENGTH_SHORT).show()
        if (vModel.numberOfAccount == 0){
            binding.buttonSearch.isEnabled = false
        }else{
            binding.buttonSearch.isEnabled = true
        }
    }

    private fun setListener() {
        binding.buttonSearch.setOnClickListener {
            var number = binding.numberEditText.text.toString()
            vModel.search(number)
        }
        val accountObserver = Observer<Account>{ account ->
            if(account != null) {
                binding.typeAccount1.text = account.type
                binding.accountNumber1.text = "شماره کارت: " + account.number
                binding.balance1.text = "موجودی: " + account.balance
            }else if(!binding.numberEditText.text.isNullOrEmpty()){
                Toast.makeText(activity, "حساب موردنظر پیدا نشد" , Toast.LENGTH_SHORT ).show()
            }
        }
        activity?.let { vModel.accountLiveData.observe(it, accountObserver) }
    }

}