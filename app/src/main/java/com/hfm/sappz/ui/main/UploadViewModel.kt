package com.hfm.sappz.ui.main

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ProgressCallback
import com.parse.SaveCallback
import org.json.JSONArray
import java.io.BufferedInputStream
import java.lang.Exception

class UploadViewModel : ViewModel() {
    val progressLiveData= MutableLiveData<Int>()

    fun upload(context:Context,bookId:String,fileUri: Uri,fileName:String,seasons:List<Int>?,tags:List<String>?){
        val obj=ParseObject("BFile")
        seasons?.let {
            val ss=JSONArray(it)
            obj.put("seasons",ss)
        }
        tags?.let {
            val tgs=JSONArray(tags)
            obj.put("tags",tgs)
        }

        val fileBytes=context.contentResolver.openInputStream(fileUri)?.readBytes()
        obj.put("name",fileName?:"UnknownName")
        obj.put("bookid",bookId)
        val file=ParseFile(fileName,fileBytes)
        file.saveInBackground({ e->
            e?.let {
                progressLiveData.value=-1
                return@saveInBackground
            }
            obj.put("file",file)
            obj.save()
            progressLiveData.value=101
        }, { percent->
            progressLiveData.value=percent
        })
    }

    fun parseRawTags(rawTags:String):List<String>?{
        return rawTags.split("-")
    }
    fun parseRawSeasons(rawSeasons:String):List<Int>?{
        return try {
            val s=rawSeasons.split(",").map {
                it.toInt()
            }
            s
        }catch (e:Exception){
            null
        }
    }

}
