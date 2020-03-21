package com.hfm.sappz.ui.main

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.ParseObject
import com.parse.ParseQuery

const val bookIdKey="bookid"        //String
const val nameKey="name"            //String
const val seasonsKey="seasons"      //Array
const val fileKey="file"            //File
const val tagsKey="tags"            //Array


class FileViewModel : ViewModel() {
    val filesLiveData = MutableLiveData<List<BFile>>()

    fun fetchFiles(bookId:String, seasons: List<Int>?=null, tags:List<String>?=null){
        val query=ParseQuery.getQuery<ParseObject>("BFile")
        query.whereEqualTo(bookIdKey,bookId)
        seasons?.let {
            query.whereContainsAll(seasonsKey,it)
        }
        tags?.let{
            query.whereContainsAll(tagsKey,tags)
        }
        query.findInBackground { objs, e ->
            e?.let {
                filesLiveData.value=null
                return@findInBackground
            }
            objs?.let {
                val bFiles = it.map {
                    val name=it.getString(nameKey)?:"unknown"
                    val file=it.getParseFile(fileKey)
                    BFile(name,bookId,url = file?.url)
                }

                filesLiveData.value=bFiles
            }
        }

    }

    fun downloadFile(context: Context, url:String){
        val req=DownloadManager.Request(Uri.parse(url))
        req.setTitle("Downloading Book File")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        val dlManager=context.getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager
        dlManager?.enqueue(req)

    }


}
data class BFile(val name:String,val bookId:String,val seasons:List<Int>?=null,val tags:List<String>?=null,val url:String?=null)