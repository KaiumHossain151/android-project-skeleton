package ai.retail.myapp.domain.auth

import java.io.Serializable

class AuthResponse : Serializable {

    var isAuthenticated = false
    var token: String? = null
    var code = 0
    var errorType: String? = null
    var message: String? = null



    override fun toString(): String {
        return "AuthResponse{" +
                "authenticated=" + isAuthenticated +
                ", token='" + token + '\'' +
                ", code=" + code +
                ", errorType='" + errorType + '\'' +
                ", message='" + message + '\'' +
                '}'
    }
}