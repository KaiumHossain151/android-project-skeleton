package ai.retail.myapp.activities

import ai.retail.myapp.adapter.PostListAdapter
import ai.retail.myapp.adapter.diffutils.PostListItemCallbacks
import ai.retail.myapp.databinding.ActivityHomeBinding
import ai.retail.myapp.network.models.Post
import ai.retail.myapp.utils.ApiState
import ai.retail.myapp.viewmodels.HomeViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity() {


    private lateinit var mBinding: ActivityHomeBinding

    private lateinit var mPostListAdapter: PostListAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var mFirebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupCrashCred()
        homeViewModel.getPost()
        lifecycleScope.launchWhenCreated {
            homeViewModel._postStateFlow.collect { it ->
                when(it){
                    is ApiState.Loading -> {
                        showProgressDialog()
                    }
                    is ApiState.Error -> {
                        cancelProgressDialog()
                        showSnackBar(it.message.toString())
                    }
                    is ApiState.Success<*> -> {
                        cancelProgressDialog()
                        setupPostList(it.data as List<Post>)
                    }
                    is ApiState.Empty -> {
                        cancelProgressDialog()
                        showSnackBar("No data available")
                    }
                }
            }
        }
    }

    private fun setupPostList(postList: List<Post>){
        mPostListAdapter.submitList(postList)
        mBinding.recyclerViewCalender.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerViewCalender.adapter = mPostListAdapter
    }

    private fun setupCrashCred() {
        setCrashlyticsUserCred(
            mFirebaseUser.displayName,
            mFirebaseUser.email,
            mFirebaseUser.phoneNumber
        )
    }

    override fun initListeners() {
        TODO("Not yet implemented")
    }

    override fun initVariables() {
        TODO("Not yet implemented")
        mPostListAdapter = PostListAdapter(PostListItemCallbacks(),this)
    }

}