package com.example.apphw13.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.apphw13.databinding.FragmentShowAccountBinding
import com.example.apphw13.models.Account
import com.example.apphw13.repository.AccountRepositpry
import com.example.apphw13.viewModels.MainViewModel

class showAccount : Fragment() {
    private val vModel : MainViewModel by activityViewModels()
    lateinit var binding : FragmentShowAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setListener()
    }

    private fun setListener() {
        binding.backBtn.setOnClickListener {
            vModel.backClicked()
        }
        binding.nextBtn.setOnClickListener {
            vModel.nextClicked()
       }

        val accountObserver = Observer<Account>{ account ->
            if(account != null) {
                binding.typeAccount1.text = account.type
                binding.accountNumber1.text = "شماره کارت: " + account.number
                binding.balance1.text = "موجودی: " + account.balance
            }
        }
        activity?.let { vModel.accountLiveData.observe(it, accountObserver) }

        val buttonNextEnabledObserver = Observer<Boolean>{ enabled ->
            binding.nextBtn.isEnabled = enabled
        }
        val buttonBackEnabledObserver = Observer<Boolean>{  enabled ->
            binding.backBtn.isEnabled = enabled
        }

        activity?.let { vModel.nextEnabledLiveData.observe(it, buttonNextEnabledObserver) }
        activity?.let { vModel.backEnabledLiveData.observe(it, buttonBackEnabledObserver) }

    }

    fun initView(){
        vModel.accountFirst()
        //Toast.makeText(activity, "${vModel.minId}", Toast.LENGTH_SHORT).show()
        if (vModel.numberOfAccount == 0){
            vModel.nextEnabledLiveData.value = false
        }else{
            vModel.nextEnabledLiveData.value = true
        }
    }

}