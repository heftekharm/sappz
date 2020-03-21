package com.hfm.sappz.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfm.sappz.databinding.BookBooksRvItemBinding
import java.util.*

class BooksRecycleViewAdapter(val books:List<Book>,val clickItemCallback:(bookId:String,booName:String)->Unit): RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding = BookBooksRvItemBinding.inflate(inflater,parent,false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book=books[position]
        holder.binding.bookBooksRvItemTextviewName.text = book.name
        holder.itemView.setOnClickListener {
            clickItemCallback(book.bid,book.name)
        }
    }

}
class BookViewHolder(public val binding: BookBooksRvItemBinding) :RecyclerView.ViewHolder(binding.root);
