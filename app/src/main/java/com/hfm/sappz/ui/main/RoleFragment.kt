package com.hfm.sappz.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.hfm.sappz.R

import com.hfm.sappz.databinding.RoleFragmentBinding
import java.lang.Exception

class RoleFragment : Fragment() {
    private var binding:RoleFragmentBinding?=null;
    private lateinit var viewModel: RoleViewModel

    companion object {
        fun newInstance() = RoleFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=RoleFragmentBinding.inflate(inflater,container,false)
        return binding?.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState);
        val navController= findNavController()
        viewModel = ViewModelProviders.of(this).get(RoleViewModel::class.java)
        viewModel.loginRespLive.observe(viewLifecycleOwner, loginRespObserver)
        binding?.roleTeacherBtn?.setOnClickListener{
            val uname:String?=binding?.roleTeacherUserEdit?.text.toString()
            val pass:String?=binding?.roleTeacherPassEdit?.text.toString()
            context?.let { it1 -> LocalStorage.saveLoginType(it1,isStudent = false) }
            viewModel.logInAsTeacher(uname,pass);
        }
        binding?.roleStudentBtn?.setOnClickListener{
            context?.let { it1 -> LocalStorage.saveLoginType(it1,isStudent = true) }
            viewModel.logInAsStudent();
        }
    }


    private val loginRespObserver=Observer<LoginResponse>{
        if(it.isAuth){
            val navController:NavController = findNavController();
            navController.navigate(R.id.action_roleFragment_to_fieldFragment)
        }else{
            val msg:String=try{resources.getString(it.msgStringId?:0)}catch (e:Exception){"Error!"}
            Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
        }
    }


}

