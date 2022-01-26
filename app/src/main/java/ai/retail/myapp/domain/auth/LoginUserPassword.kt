package ai.retail.myapp.domain.auth

import java.io.Serializable

class LoginUserPassword : Serializable {
    var username: String? = null
    var password: String? = null

    constructor() {}
    constructor(username: String?, password: String?) {
        this.username = username
        this.password = password
    }

    override fun toString(): String {
        return "LoginUserPassword{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}'
    }
}