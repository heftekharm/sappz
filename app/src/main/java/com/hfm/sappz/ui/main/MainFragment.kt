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
import com.parse.Parse
import com.parse.ParseUser

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val navController:NavController=findNavController();
        val currentUser= ParseUser.getCurrentUser()
        if( currentUser != null ){
            navController.navigate(R.id.action_mainFragment_to_fieldFragment)
        }else{
            navController.navigate(R.id.action_mainFragment_to_roleFragment)
        }
    }

}
