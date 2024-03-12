package com.data.dhirajapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FormTestVM @Inject constructor(private val testRepo: TestRepo): ViewModel() {

    var extraId=""
    var test:Test?=null
    var testString= MutableLiveData("")
    var testString2= MutableLiveData("")
    fun fetchItem(){
        viewModelScope.launch {
            try {
                testRepo.get(extraId)?.let {
                    test=it
                    testString.value=it.title?:""
                    testString2.value=it.phone?:""
                }
            }catch (e:Exception){

            }
        }
    }

    fun saveItem(){
        viewModelScope.launch {
            try {
                if(test==null)test=Test()
                test?.title=testString.value?:""
                test?.phone=testString2.value?:""
                test?.let {
                    testRepo.save(it)
                }
            }catch (e:Exception){

            }
        }
    }

    fun updateItem(){
        viewModelScope.launch {
            try {
                if(test==null)test=Test()
                test?.title=testString.value?:""
                test?.phone=testString2.value?:""
                test?.let {
                    testRepo.update(it)
                }
            }catch (e:Exception){

            }
        }
    }

}