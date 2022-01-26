package ai.retail.myapp.utils

object ListUtils {
    fun isEmpty(objects: List<Any?>?): Boolean {
        return objects == null || objects.size == 0
    }
}