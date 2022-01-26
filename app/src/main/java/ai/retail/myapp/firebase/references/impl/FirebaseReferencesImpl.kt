package ai.retail.myapp.firebase.references.impl

import ai.retail.myapp.firebase.datamodels.Merchandiser
import ai.retail.myapp.firebase.references.FirebaseReferences
import ai.retail.myapp.utils.Resource
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference

class FirebaseReferencesImpl constructor(val databaseReference: DatabaseReference):
    FirebaseReferences {

    private val MERCHANDISER_NOT_FOUND = "Merchandiser not found"

    private val NODE_MERCHANDISERS = "merchandisers"
    private val NODE_BASIC_INFO = "basic_info"

    private val mLiveMerchandiser: MutableLiveData<Resource<Merchandiser>> =
        MutableLiveData<Resource<Merchandiser>>()




//    override fun getMerchandiserInfo(whitelistingNumber: String): LiveData<Resource<Merchandiser>> {
//        mLiveMerchandiser.value = Resource.loading(null)
//
//        if (whitelistingNumber == null){
//            mLiveMerchandiser.value = Resource.error(MERCHANDISER_NOT_FOUND, null)
//            return mLiveMerchandiser
//        }
//
//        databaseReference.child(NODE_MERCHANDISERS).child(whitelistingNumber).child(NODE_BASIC_INFO).addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()){
//                    var merchandiser : Merchandiser? = snapshot.getValue(Merchandiser::class.java)
//
//                    mLiveMerchandiser.value = Resource.success(merchandiser)
//                }else{
//                    mLiveMerchandiser.value = Resource.error(MERCHANDISER_NOT_FOUND, null)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                mLiveMerchandiser.value = Resource.error(MERCHANDISER_NOT_FOUND, null)
//
//            }
//
//        })
//
//        return mLiveMerchandiser
//    }


}