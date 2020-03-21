package com.hfm.sappz.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.hfm.sappz.R
import com.hfm.sappz.databinding.BookFragmentBinding

class BookFragment : Fragment() {
    var binding:BookFragmentBinding?=null

    companion object {
        fun newInstance() = BookFragment()
    }

    private lateinit var viewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=BookFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        viewModel.booksLiveData.observe(viewLifecycleOwner,onBooksObserver)
        context?.let { viewModel.fetchBooks(it) }
    }

    private val onBooksObserver= Observer<List<Book>>{
        if(it==null){
            Toast.makeText(context, "دریافت کتاب با مشکل مواجه شد", Toast.LENGTH_SHORT).show();
            return@Observer
        }
        if(it.isNotEmpty()){
            val booksRv=binding?.bookBooksRecycleview;
            booksRv?.layoutManager=LinearLayoutManager(context)
            var booksRvAdapter:BooksRecycleViewAdapter= BooksRecycleViewAdapter(it, onSelectBook)
            booksRv?.adapter=booksRvAdapter
        }
    }

    private val onSelectBook={ bookId:String,name:String->
        val args=Bundle();
        args.putString("bookId",bookId)
        args.putString("bookName",name)
        findNavController().navigate(R.id.action_bookFragment_to_fileFragment,args);
    }

}
