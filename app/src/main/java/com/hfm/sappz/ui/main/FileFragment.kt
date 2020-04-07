package com.hfm.sappz.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.hfm.sappz.R
import com.hfm.sappz.databinding.FileFragmentBinding
import com.parse.ParseFile
import com.parse.ParseFileUtils

class FileFragment : Fragment() {
    var binding:FileFragmentBinding?=null
    private var filesAdapter: FilesRecycleViewAdapter?=null

    companion object {
        fun newInstance() = FileFragment()
    }

    private lateinit var viewModel: FileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FileFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bookId=arguments?.getString("bookId")
        val bookName=arguments?.getString("bookName")
        binding?.fileBookNameTextview?.text=bookName
        viewModel = ViewModelProviders.of(this).get(FileViewModel::class.java)
        viewModel.filesLiveData.observe(viewLifecycleOwner,filesObserver)
        bookId?.let { viewModel.fetchFiles(it) }
        initRecycleViewFiles()
        if(LocalStorage.loggedInAsStudent(context!!)){
            binding?.fileFileUploadBtn?.hide()
        }else{

            binding?.fileFileUploadBtn?.setOnClickListener{
                findNavController().navigate(R.id.action_fileFragment_to_uploadFragment,arguments)
            }
        }
    }

    private fun initRecycleViewFiles(){
        filesAdapter=FilesRecycleViewAdapter(ArrayList<BFile>()) { url,name->
            context?.let { viewModel.downloadFile(it,url,name) }
        }
        binding?.fileFilesRecycleview?.adapter=filesAdapter
        binding?.fileFilesRecycleview?.layoutManager=LinearLayoutManager(context)
    }


    private val filesObserver=Observer<List<BFile>>{
        binding?.fileFilesNumTextview?.text=it?.size.toString()
        filesAdapter?.addBFiles(it)
    }

}
