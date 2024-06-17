package com.example.myselfcustom.arch.livedatabus

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy

class LiveDataBus {

    val liveDatas by lazy { mutableMapOf<String, LiveEventObserver<*>>() }

    @Synchronized
    private fun <T> bus(channel: String): LiveEventObserver<T> {
        return liveDatas.getOrPut(channel) {
            LiveDataEvent<T>(channel)
        } as LiveEventObserver<T>
    }

    private fun < T> with(channel: String, clz: Class<T>): LiveEventObserver<T> {
        return bus(channel)
    }

    fun <E> of(clz: Class<E>): E {
        if (!clz.isInterface) {
            throw IllegalArgumentException("clz must be interface")
        }
        // todo 确认数值
        if (clz.interfaces.size > 1) {
            throw IllegalArgumentException("clz must be not include other interface")
        }
        return Proxy.newProxyInstance(clz.classLoader, arrayOf(clz)) { proxy, method, args ->
            return@newProxyInstance get().with(
                "${clz.canonicalName}_${method.name}",
                (method.genericReturnType as ParameterizedType).actualTypeArguments[0].javaClass
            )
        } as E
    }

    companion object {
        private val INSTANCE by lazy { LiveDataBus() }

        fun get() = INSTANCE

    }

}