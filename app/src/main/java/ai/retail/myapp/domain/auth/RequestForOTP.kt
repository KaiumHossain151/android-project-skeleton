package ai.retail.myapp.domain.auth

import java.io.Serializable

class RequestForOTP(var reporter: String?, private val otp_secret: String?) : Serializable {
    override fun toString(): String {
        return "RequestForOTP{" +
                "reporter='" + reporter + '\'' +
                ", otp_secret='" + otp_secret + '\'' +
                '}'
    }
}