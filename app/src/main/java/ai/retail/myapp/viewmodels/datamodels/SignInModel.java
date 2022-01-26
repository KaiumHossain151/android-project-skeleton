package ai.retail.myapp.viewmodels.datamodels;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import ai.retail.myapp.BR;


public class SignInModel extends BaseObservable {
    private String phoneNumber;
    private boolean isValid;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (TextUtils.equals(this.phoneNumber, phoneNumber)) return;
        this.phoneNumber = phoneNumber;
        setValid(checkValidity());
    }

    private boolean checkValidity() {
        return !TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11;
    }

    @Bindable
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        if (isValid == valid) return;
        isValid = valid;
        notifyPropertyChanged(BR.valid);
    }
}
