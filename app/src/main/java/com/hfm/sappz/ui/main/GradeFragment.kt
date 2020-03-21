package com.hfm.sappz.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.hfm.sappz.R
import com.hfm.sappz.databinding.GradeFragmentBinding

class GradeFragment : Fragment() {
    private var binding:GradeFragmentBinding?=null
    companion object {
        fun newInstance() = GradeFragment()
    }

    private lateinit var viewModel: GradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=GradeFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GradeViewModel::class.java)

        val field:String?=arguments?.getString("field")
        if (field==null){
            findNavController().popBackStack()
        }

        binding?.grade10Btn?.setOnClickListener{
            val grade=10
            onSelectGrade(context,grade)
        }
        binding?.grade11Btn?.setOnClickListener{
            val grade=11
            onSelectGrade(context,grade)
        }
        binding?.grade12Btn?.setOnClickListener{
            val grade=12
            onSelectGrade(context,grade)
        }
    }

    private fun onSelectGrade(context: Context?, grade:Int){
        val field:String?=arguments?.getString("field")//isn't null because already has been checked
        context?.let {
            viewModel.save(it, field!!, grade)
            findNavController().navigate(R.id.action_gradeFragment_to_bookFragment)
        }
    }

}
