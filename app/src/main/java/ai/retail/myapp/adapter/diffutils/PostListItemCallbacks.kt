package ai.retail.myapp.adapter.diffutils

import ai.retail.myapp.network.models.Post
import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil

class PostListItemCallbacks : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return TextUtils.equals(oldItem.body,newItem.body)
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return TextUtils.equals(oldItem.body,newItem.body)
    }
}