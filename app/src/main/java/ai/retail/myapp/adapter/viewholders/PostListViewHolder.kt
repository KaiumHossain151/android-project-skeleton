package ai.retail.myapp.adapter.viewholders

import ai.retail.myapp.adapter.PostListAdapter
import ai.retail.myapp.databinding.ItemViewerPostBinding
import ai.retail.myapp.network.models.Post
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PostListViewHolder(private val mBinding: ItemViewerPostBinding, onItemClickListener: PostListAdapter.OnItemClickListener, context: Context) : RecyclerView.ViewHolder(mBinding.root), View.OnClickListener {
    private lateinit var mPostModel: Post
    private val mOnItemClickListener: PostListAdapter.OnItemClickListener = onItemClickListener
    private val mContext: Context = context


    fun bindTo(item: Post) {
        mPostModel = item

        mBinding.tasks.text = mPostModel.body
    }

    override fun onClick(view: View) {
        mOnItemClickListener.onItemClick(mPostModel)
    }

}
