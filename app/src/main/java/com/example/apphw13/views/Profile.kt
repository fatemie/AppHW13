package com.example.apphw13.views

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.apphw13.R
import com.example.apphw13.databinding.FragmentProfileBinding

const val NAME = "NAME"
const val FATHERNAME = "FATHERNAME"
const val POSTALCODE = "POSTALCODE"
const val PHONENUMBER = "PHONENUMBER"
const val ACCOUNTCOUNT = "ACCOUNTCOUNT"

class profile : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileIsCreated()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListener()
    }

    private fun setListener() {
        binding.buttonRegister.setOnClickListener {
            saveInfo()
        }
    }

    private fun saveInfo() {
        if(checkInfoIsCorrect()) {
            prefs = requireActivity().getSharedPreferences(
                resources.getString(R.string.app_name),
                AppCompatActivity.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString(NAME, binding.editTextPersonName.text.toString())
            editor.putString(FATHERNAME, binding.editTextFatherPersonName.text.toString())
            editor.putString(PHONENUMBER, binding.editTextPhoneNumber.text.toString())
            editor.putString(POSTALCODE, binding.editTextPostalCode.text.toString())
            editor.putString(ACCOUNTCOUNT, binding.editTextAccountCount.text.toString())
            editor.apply()
            //Toast.makeText(activity, "information saved!" , Toast.LENGTH_SHORT ).show()
            goToShowProfile()
        }
    }

    private fun goToShowProfile() {
        val action = profileDirections.actionProfileToShowProfile()
        findNavController().navigate(action)
    }

    fun initViews(){
        prefs = requireActivity().getSharedPreferences(resources.getString(R.string.app_name),
            AppCompatActivity.MODE_PRIVATE
        )
        val name = prefs.getString(NAME,"")
        val fatherName = prefs.getString(FATHERNAME,"")
        val phone =  prefs.getString(PHONENUMBER, "")
        val post =  prefs.getString(POSTALCODE, "")
        val accountCount =  prefs.getString(ACCOUNTCOUNT, "")
        if(!name.isNullOrEmpty()){
            binding.editTextPersonName.setText(name)
            binding.editTextPhoneNumber.setText(phone)
            binding.editTextFatherPersonName.setText(fatherName)
            binding.editTextPostalCode.setText(post)
            binding.editTextAccountCount.setText(accountCount)
        }
    }

    fun profileIsCreated(){
        prefs = requireActivity().getSharedPreferences(resources.getString(R.string.app_name),
            AppCompatActivity.MODE_PRIVATE
        )

        val edit = prefs.getBoolean(EDIT,false)
        val name = prefs.getString(NAME,"")
        if(name.isNullOrEmpty()){
            goToShowProfile()
        }else if (!edit){
            goToShowProfile()
        }else{
            val editor =  prefs.edit()
            editor.putBoolean(EDIT,false)
            editor.apply()
        }
    }

    fun checkInfoIsCorrect() :Boolean{
        val post = binding.editTextPostalCode.text
        val phone = binding.editTextPhoneNumber.text
        val accountCount = binding.editTextAccountCount.text
        val intAccountCount = Integer.parseInt(accountCount.toString())
        if(post.length != 10){
            binding.editTextPostalCode.setError("کد پستی باید 10 رقم داشته باشد")
            return false
        }
        if (phone.length != 11 && phone.get(0) != '0'){
            binding.editTextPhoneNumber.setError("شماره تلفن صحیح وارد نشده است!")
            return false
        }
        if(intAccountCount > 5){
            binding.editTextAccountCount.setError("حداکثر 5 کارت می توانید وارد کنید!")
            return false
        }

        return true
    }


}