package com.hfm.sappz.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser

const val bidKey="bid"
const val fieldKey="field"
const val gradeKey="grade"
const val avatarKey="avatar"

class BookViewModel : ViewModel() {
    val booksLiveData=MutableLiveData<List<Book>>()

    fun fetchBooks(context: Context){
        val field=LocalStorage.getField(context)?:return
        val grade=LocalStorage.getGrade(context)

        val query=ParseQuery.getQuery<ParseObject>("Book")
        query.whereEqualTo(fieldKey,field).whereEqualTo(gradeKey,grade).findInBackground{objs,e->
            e?.let {
                e.printStackTrace()
                booksLiveData.value=null
                return@findInBackground
            }
            objs?.let {
                val books=objs.map {
                    val name=it.getString(nameKey)?:"unknown"
                    val bid=it.getString(bidKey)?:"unknown"
                    val avatar=it.getParseFile(avatarKey)
                    Book(bid,name,field,grade)
                }
                booksLiveData.value=books
            }
        }

    }

}
data class Book(val bid:String,val name:String ,val field:String ,val grade:Int , val avatar:String?=null)