@file:Suppress("UNCHECKED_CAST")

package 间

fun <T> Any.toAny(): T {

    return this as T

}