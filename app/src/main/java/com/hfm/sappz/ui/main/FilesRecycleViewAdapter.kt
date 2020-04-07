package com.hfm.sappz.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hfm.sappz.databinding.BookBooksRvItemBinding
import com.hfm.sappz.databinding.FileFilesRecycleviewItemBinding

class FilesRecycleViewAdapter(val files:ArrayList<BFile>,val dlfunc:(url:String,name:String)->Unit): RecyclerView.Adapter<FileViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
            val inflater= LayoutInflater.from(parent.context)
            val binding = FileFilesRecycleviewItemBinding.inflate(inflater,parent,false)
            return FileViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return files.size
        }

        override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
            val file=files[position]
            holder.binding.fileFilesRvItemTextviewName.text = file.name
            holder.binding.fileFilesRvItemDownloadBtn.setOnClickListener{
                if(file.url!=null){
                    dlfunc(file.url,file.name)
                }else{
                    Toast.makeText(holder.itemView.context, "آدرس فایل وجود ندارد", Toast.LENGTH_SHORT).show();
                }
            }
        }

    fun addBFiles(fs: List<BFile>) {
        files.addAll(fs)
        notifyDataSetChanged()
    }
}

class FileViewHolder(val binding: FileFilesRecycleviewItemBinding) : RecyclerView.ViewHolder(binding.root);


