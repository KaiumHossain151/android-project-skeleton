package ai.retail.myapp.viewmodels

import ai.retail.myapp.viewmodels.datamodels.SignInModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    val  signInModel : SignInModel = SignInModel();
}