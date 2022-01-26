package ai.retail.myapp.utils

sealed class ApiState{
    object Loading : ApiState()
    class Error(val message : Throwable) : ApiState()
    class Success<out T>(val data: T?) : ApiState()
    object Empty : ApiState()
}
