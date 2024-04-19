package com.example.myselfcustom.coroutinetest

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

class CoroutineTest2 {

    @Test
    fun test1() {
        runBlocking {
            launch(Dispatchers.Default + CoroutineName("test")) {
                println("I'm working on thread ${Thread.currentThread().name}")
            }
        }
    }

    @Test
    fun test2() {
        runBlocking {
            launch {
                for (k in 1..3) {
                    println("I'm not block $k")
                    delay(100)
                }
            }
            simple().collect { value -> println(value) }
        }
    }

    /**
     * flow：类似于冷流stateFlow，直到collect时才会运行
     */
    @Test
    fun test3() {
        runBlocking {
            val flow = simple()
            println("call collect")
            flow.collect()
        }
    }

    @Test
    fun test4() {
        runBlocking {
            (1..3).asFlow()
                .map { request ->  performRequest(request)}
                .collect {
                    println(it)
                }
        }
    }

    @Test
    fun test5() {
        runBlocking {
            (1..3).asFlow()
                .transform {
                    emit(performRequest(it))
                    emit("next is ${it + 1}")
                }
                .collect {result -> println(result) }
        }
    }

    @Test
    fun test6() {
        runBlocking {
//            val time = measureTimeMillis {
//                simple().collect { value ->
//                    delay(100)
//                    println(value)
//                }
//            }
//            println("time cost: $time")

//            val time = measureTimeMillis {
//                simple()
//                    .buffer()
//                    .collect { value ->
//                    delay(100)
//                    println(value)
//                }
//            }

            val time = measureTimeMillis {
                simple()
                    .conflate()
                    .collect { value ->
                        delay(300)
                        println(value)
                    }
            }
            println("time cost: $time")
        }
    }

    @Test
    fun test7() {
        runBlocking {
            val nums = listOf(1, 2, 3).asFlow()
            val str = listOf("a", "b", "c", "d").asFlow()
            nums.zip(str) {a, b -> "$a -> $b"}
                .collect {
                    println(it)
                }
        }
    }

    @Test
    fun test8() {
        runBlocking {
            val nums = (1..3).asFlow()
            nums.onEach { delay(100) }
                .flatMapConcat { requestFlow(it) }
                .collect {
                    println(it)
                }
        }
    }

    /**
     * 取消上一次flow的collect，只保留最新的flow收集
     */
    @Test
    fun test9() {
        runBlocking {
            val nums = (1..3).asFlow()
            nums.onEach { delay(100) }
                .flatMapLatest { requestFlow(it) }
                .onCompletion {
                    println("Done")
                }
                .collect {
                    println(it)
                }
        }
    }

    @Test
    fun test10() {
        runBlocking {
            event()
                .onEach { event -> println("event: $event") }
                .launchIn(this)
//                .collect {
//                    println("hha")
//                }
            println("Done!")
        }
    }

    @Test
    fun test11() {
        runBlocking {
            try {
                foo()
                    .onCompletion { cause -> cause?.printStackTrace() }
                    .catch { cause -> cause.printStackTrace() }
                    .collect {
                        if (it >= 3) cancel()
                        println(it)
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    @Test
    fun test12() {
        runBlocking {
            (1..3)
                .asFlow()
//                .cancellable()
                .collect {
                    if (it == 2) cancel()
                    println(it)
                }
        }
    }


    private fun foo(): Flow<Int> = flow {
        for (i in 1..3) {
            println("emit: $i")
            emit(i)
        }
    }

    private fun event(): Flow<Int> =
        (1..3).asFlow().onEach {
            delay(100)
        }/*.flowOn(Dispatchers.Default)*/

    suspend fun performRequest(request: Int) : String {
        delay(100)
        return "response: $request"
    }

    fun requestFlow(i: Int) = flow<String> {
        emit("$i first")
        delay(500)
        emit("$i second")
    }


    private fun simple() = flow<Int> {
        println("flow start")
        for (i in 1..3) {
            delay(100)
            emit(100)
        }
    }

}