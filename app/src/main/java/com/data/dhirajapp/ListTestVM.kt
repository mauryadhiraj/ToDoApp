package com.data.dhirajapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ListTestVM @Inject constructor(private val testRepo: TestRepo):ViewModel(){

    private val testListLiveData: MutableLiveData<MutableList<Test>> = MutableLiveData()

    fun onFetchTest():MutableLiveData<MutableList<Test>>{
        return testListLiveData
    }

    fun fetchTest(){
        viewModelScope.launch {
            try {
                testRepo.getAll()?.let {
                    testListLiveData.postValue(it)
                }
            }catch (e:Exception){

            }
        }
    }
}