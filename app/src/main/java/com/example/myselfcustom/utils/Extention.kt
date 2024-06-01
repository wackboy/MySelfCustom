
package com.example.myselfcustom.utils

import android.content.res.Resources
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.Locale

fun Int.dp(): Float {
    return (this * Resources.getSystem().displayMetrics.density + 0.5f)
}

fun Int.sp(): Float {
    return (this * Resources.getSystem().displayMetrics.scaledDensity + 0.5f)
}

fun <T> T.throttle(during: Long = 500L): T {
    return Proxy.newProxyInstance(this!!::class.java.classLoader, this!!::class.java.interfaces,
        object : InvocationHandler {

            private var lastInvokeTime = System.currentTimeMillis()

            override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
                val current = System.currentTimeMillis()
                return if (current - lastInvokeTime > during) {
                    lastInvokeTime = current
                    method.invoke(this@throttle, *args.orEmpty())
                } else {
                    resolveDefaultReturnValue(method)
                }
            }

        }
    ) as T
}

private fun resolveDefaultReturnValue(method: Method): Any? {
    return when (method.returnType.name.lowercase(Locale.US)) {
        Void::class.java.simpleName.lowercase(Locale.US) -> null
        else -> throw IllegalArgumentException("throttle只用于无返回值函数")
    }
}

fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}