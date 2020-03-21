package com.hfm.sappz.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.hfm.sappz.R
import com.hfm.sappz.databinding.FieldFragmentBinding

class FieldFragment : Fragment() {
    private var binding:FieldFragmentBinding?=null
    companion object {
        fun newInstance() = FieldFragment()
    }

    private lateinit var viewModel: FieldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FieldFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FieldViewModel::class.java)
        binding?.fieldElcBtn?.setOnClickListener{
            val field="elc"
            navigate(field)
        }

        binding?.fieldHumBtn?.setOnClickListener{
            val field="hum"
            navigate(field)
        }
    }

    private fun navigate(field:String){
        val navController:NavController=findNavController()
        val bundle=Bundle()
        bundle.putString("field",field);
        navController.navigate(R.id.action_fieldFragment_to_gradeFragment,bundle)
    }

}
