package com.data.dhirajapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.data.virat.databinding.FormTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormTest: AppCompatActivity() {

    private lateinit var binding: FormTestBinding
    private val vm by viewModels<FormTestVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=FormTestBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureActivity()
        initializeComponenets()
    }

    fun configureActivity(){
        binding.vm=vm
        vm.extraId=intent.getStringExtra(EXTRA_ID)?:""

        if(vm.extraId.isNotEmpty()){     //check extraId
            binding.btnSave.text="Update" //
            vm.fetchItem()
        }
    }
    fun initializeComponenets(){
        initializeListener()
    }

    fun initializeListener(){
        binding.btnSave.setOnClickListener{
            if(vm.extraId.isEmpty()){
                vm.saveItem()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)

            }else{
                vm.updateItem()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)}
        }
    }
    companion object{
        const val EXTRA_ID="EXTRA_ID"

    }
}