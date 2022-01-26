package ai.retail.myapp.utils

class mapUtils {
    companion object{
        fun isEmpty(objects: Map<out Any?, Any?>?): Boolean {
            return objects == null || objects.isEmpty()
        }
    }
}