package ai.retail.myapp.viewmodels.datamodels;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import ai.retail.myapp.BR;
import ai.retail.myapp.utils.Constants;

public class CodeVerificationModel extends BaseObservable implements Parcelable {
    private String code, phoneNumber;
    private boolean isValid;
    private boolean isPasswordValid;
    private int resend_count = Constants.DEFAULT_INT;
    private boolean is_password_mode_active;
    private boolean is_password_login_button_active;
    private String password;


    public CodeVerificationModel() {
    }

    protected CodeVerificationModel(Parcel in) {
        code = in.readString();
        phoneNumber = in.readString();
        isValid = in.readByte() != 0;
        is_password_mode_active = in.readByte() != 0;
        password = in.readString();
        resend_count = in.readInt();
        is_password_login_button_active = in.readByte() != 0;
        isPasswordValid = in.readByte() != 0;
    }

    @Bindable
    public boolean isIs_password_login_button_active() {
        return is_password_login_button_active;
    }

    public void setIs_password_login_button_active(boolean is_password_login_button_active) {
        this.is_password_login_button_active = is_password_login_button_active;
        if (this.is_password_login_button_active)
            notifyPropertyChanged(BR.is_password_login_button_active);
    }

    public int getResend_count() {
        return resend_count;
    }

    public void setResend_count() {
        if (this.resend_count < 4){
            this.resend_count ++;
//            Log.d("ResendCount_if" ,""+this.resend_count);
        } else {
            setIs_password_login_button_active(Constants.TRUE);
//            Log.d("ResendCount_else",""+this.resend_count);
        }


    }

    public static final Creator<CodeVerificationModel> CREATOR = new Creator<CodeVerificationModel>() {
        @Override
        public CodeVerificationModel createFromParcel(Parcel in) {
            return new CodeVerificationModel(in);
        }

        @Override
        public CodeVerificationModel[] newArray(int size) {
            return new CodeVerificationModel[size];
        }
    };

    @Bindable
    public boolean isIs_password_mode_active() {
        return is_password_mode_active;
    }

    public void setIs_password_mode_active(boolean is_password_mode_active) {
        this.is_password_mode_active = is_password_mode_active;
        if (is_password_mode_active)
            notifyPropertyChanged(BR.is_password_mode_active);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (TextUtils.equals(this.password, password)) return;
        this.password = password;
        setPasswordValid(checkPasswordValidity());
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (TextUtils.equals(this.code, code)) return;
        this.code = code;
        setValid(checkValidity());
    }

    private boolean checkValidity() {
        return !TextUtils.isEmpty(code) && code.length() == 6;
    }

    private boolean checkPasswordValidity() {
        return !TextUtils.isEmpty(password) && password.length() == 6;
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

    @Bindable
    public boolean isPasswordValid() {
        return isPasswordValid;
    }

    public void setPasswordValid(boolean passwordValid) {
        if (isPasswordValid == passwordValid) return;
        isPasswordValid = passwordValid;
        notifyPropertyChanged(BR.passwordValid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(phoneNumber);
        dest.writeByte((byte) (isValid ? 1 : 0));
        dest.writeByte((byte) (is_password_mode_active ? 1 : 0));
        dest.writeString(password);
        dest.writeInt(resend_count);
        dest.writeByte((byte) (is_password_login_button_active ? 1 : 0));
        dest.writeByte((byte) (isPasswordValid ? 1 : 0));

    }
}

