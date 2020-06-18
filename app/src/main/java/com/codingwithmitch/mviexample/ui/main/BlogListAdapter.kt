package com.codingwithmitch.mviexample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.model.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*

class BlogListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<BlogPost>() {

        override fun areItemsTheSame(oldItem: BlogPost, newItem: BlogPost): Boolean {
            return oldItem.primaryKey == newItem.primaryKey
        }

        override fun areContentsTheSame(oldItem: BlogPost, newItem: BlogPost): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return BlogPostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_blog_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogPostViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<BlogPost>) {
        differ.submitList(list)
    }

    class BlogPostViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: BlogPost) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.blog_title.text = item.title
            Glide.with(itemView.context)
                .load(item.image)
                .into(itemView.blog_image)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: BlogPost)
    }
}