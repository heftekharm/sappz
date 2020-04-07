package com.hfm.sappz.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hfm.sappz.databinding.UploadFragmentBinding


class UploadFragment : Fragment() {
    var binding:UploadFragmentBinding?=null
    private val filePickerReqCode=1777
    private var bookId:String?=null

    private var fileUri: Uri?=null
    private var fileName:String?=null
    companion object {
        fun newInstance() = UploadFragment()
    }

    private lateinit var viewModel: UploadViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=UploadFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UploadViewModel::class.java)
        bookId=arguments?.getString("bookId")
        val bookName=arguments?.getString("bookName")
        binding?.uploadSelectFileBtn?.setOnClickListener{
            selectFile()
        }

        binding?.uploadUploadBtn?.setOnClickListener{
            if (fileUri==null){
                Toast.makeText(context, "فایلی انتخاب نشده است", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
            val rawTags=binding?.uploadTagsEdittext?.text.toString()
            val tags=viewModel.parseRawTags(rawTags)
            val rawSeasons=binding?.uploadSeasonsEdittext?.text.toString()
            val seasons=viewModel.parseRawSeasons(rawSeasons)
            viewModel.progressLiveData.observe(viewLifecycleOwner,fileUploadObserver)
            viewModel.upload(context!!,bookId!!,fileUri!!,fileName!!,seasons,tags)
        }
    }

    private fun selectFile(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        startActivityForResult(intent, filePickerReqCode)
    }

    @SuppressLint("SetTextI18n")
    private val fileUploadObserver=Observer<Int>{
        if(it<0){
            binding?.uploadProgressIndicator?.text="خطا!"
            return@Observer
        }
        if(it==101){
            Toast.makeText(context, "بارگذاری با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
            return@Observer
        }
        binding?.uploadProgressIndicator?.text="$it%"

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK && requestCode==filePickerReqCode){
            fileUri = data?.data
            if (fileUri==null) return
            val fileCursor=  context?.contentResolver?.query(fileUri!!,null,null,null,null)
            fileName=if((fileCursor?.count)?:0 > 0 ){
                fileCursor?.moveToFirst()
                fileCursor?.getString(fileCursor.getColumnIndex("_display_name"))
            }else{
                "UnknownName"
            }
            fileCursor?.close()
            binding ?. uploadSelectedFileTextview ?. text=fileName
        }
    }
}
