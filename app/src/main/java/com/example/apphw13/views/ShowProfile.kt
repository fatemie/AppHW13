package com.example.apphw13.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.apphw13.R
import com.example.apphw13.databinding.FragmentShowProfileBinding
const val EDIT = "EDIT"

class ShowProfile : Fragment() {
    lateinit var binding: FragmentShowProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        initViews()
    }

    private fun initViews() {
        var prefs = requireActivity().getSharedPreferences(resources.getString(R.string.app_name),
            AppCompatActivity.MODE_PRIVATE
        )
        binding.textViewName.text = "نام و نام خانوادگی: " + prefs.getString(NAME,"")
        binding.textViewFatherName.text = "نام پدر: " + prefs.getString(FATHERNAME,"")
        binding.textViewPostalCode.text = "کد پستی: " + prefs.getString(POSTALCODE,"")
        binding.textViewPhoneNumber.text = "شماره تلفن: " + prefs.getString(PHONENUMBER,"")
        binding.textViewAccountCount.text = "تعداد شماره حساب: " + prefs.getString(ACCOUNTCOUNT,"")
    }

    private fun setListener() {
        binding.buttonEdit.setOnClickListener {
            goBackForEdit()
        }
    }

    private fun goBackForEdit() {
        var prefs = requireActivity().getSharedPreferences(resources.getString(R.string.app_name),
            AppCompatActivity.MODE_PRIVATE
        )
        val editor =  prefs.edit()
        editor.putBoolean(EDIT, true)
        editor.apply()
        val action = ShowProfileDirections.actionShowProfileToProfile()
        findNavController().navigate(action)
    }


}