package com.data.dhirajapp
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import com.data.virat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TestAdapterListener {

    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<ListTestVM>()
    private lateinit var testAdapter: TestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureActivity()
        initializeComponent()
    }

    override fun onResume() {
        super.onResume()
        vm.fetchTest()
    }

    private fun configureActivity() {
        binding.lifecycleOwner = this
    }

    private fun initializeComponent() {
        initializeUI()
        initializedData()
        initializeListener()
    }

    private fun initializeUI() {
        testAdapter = TestAdapter(mutableListOf(), this,)
        binding.adapterTest = testAdapter
    }

    private fun initializedData() {
        vm.onFetchTest().observe(this,) {
            testAdapter.updateList(it)
            testAdapter.notifyDataSetChanged()
        }
    }

    private fun initializeListener() {
        binding.btnAdd.setOnClickListener {
            val toform = Intent(this, FormTest::class.java)
            startActivity(toform)
        }
    }

    override fun onTestAdapterClick(clickAction: ClickAction, test: Test) {

        when (clickAction) {
            ClickAction.PHONE -> {
                test.phone?.let { phone ->
                    if (phone.isNotEmpty()) {
                        val toPhone = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:$phone")
                        }
                        toPhone.resolveActivity(packageManager)?.let {
                            startActivity(toPhone)
                        }
                    }
                }
            }

            else -> {

                test._localId?.let { id ->
                    val intent = Intent(this, FormTest::class.java)
                    intent.putExtra(FormTest.EXTRA_ID, id)
                    startActivity(intent)
                }
            }
        }
    }
}