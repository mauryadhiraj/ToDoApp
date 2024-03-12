package com.data.dhirajapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.data.virat.R
import com.data.virat.databinding.ViewMainBinding

class TestAdapter (

    private var testList:MutableList<Test>,
    private val testAdapterListener: TestAdapterListener
):RecyclerView.Adapter<TestAdapter.ViewHolder>(){

    fun updateList(testListNew:MutableList<Test>){
        testList=testListNew
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
      val binding: ViewMainBinding =DataBindingUtil.inflate(
          LayoutInflater.from(parent.context), R.layout.view_main,parent,false
      )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestAdapter.ViewHolder, position: Int) {
        holder.bind(testList[position])
    }

    override fun getItemCount(): Int {
      return testList.size
    }

        open inner class ViewHolder(private val binding: ViewMainBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(test: Test){
                binding.tvTitle.text=getTestName(test)
                binding.tvPhone.text=getTestPhone(test)

                binding.containerRoot.setOnClickListener {
                    testAdapterListener.onTestAdapterClick(ClickAction.OPEN,test)
                }
                binding.btnDial.setOnClickListener{
                    testAdapterListener.onTestAdapterClick(ClickAction.PHONE,test)
                }
                binding.btnEdit.setOnClickListener{
                    testAdapterListener.onTestAdapterClick(ClickAction.EDIT,test)
                }
            }
        }
            fun getTestName(test:Test):String{
                return test.title?:""
            }
            fun getTestPhone(test: Test):String{
                return test.phone?:""
    }
}