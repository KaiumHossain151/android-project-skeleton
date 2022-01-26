package ai.retail.myapp.adapter

import ai.retail.myapp.R
import ai.retail.myapp.adapter.viewholders.PostListViewHolder
import ai.retail.myapp.databinding.ItemViewerPostBinding
import ai.retail.myapp.network.models.Post
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class PostListAdapter(diffCallback: DiffUtil.ItemCallback<Post>, private val mContext: Context) : ListAdapter<Post,  PostListViewHolder?>(diffCallback) {
    private lateinit var mOnItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val binding: ItemViewerPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_viewer_post, parent, false)
        return PostListViewHolder(binding, mOnItemClickListener, mContext)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        if (onItemClickListener != null) {
            mOnItemClickListener = onItemClickListener
        }
    }

    interface OnItemClickListener {
        fun onItemClick(model : Post)
    }
}