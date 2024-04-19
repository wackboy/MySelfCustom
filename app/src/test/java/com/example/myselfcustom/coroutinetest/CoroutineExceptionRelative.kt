package com.example.myselfcustom.coroutinetest

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.junit.Test

class CoroutineExceptionRelative {

    @Test
    fun test1() {
        runBlocking {
            val job = GlobalScope.launch {
                println("Throw exception from launch")
//                throw IndexOutOfBoundsException()
            }
            job.join()
            println("Joined failed job")
            val deferred =GlobalScope.async {
                println("Throwing exception from async")
                throw ArithmeticException()
            }
            try {
                deferred.await()
                println("Unreached")
            } catch (e: ArithmeticException) {
                println("caught ArithmeticException")
            }
        }
    }

    @Test
    fun test2() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) {
                delay(100)
                throw AssertionError()
            }
            val deferred = GlobalScope.async(handler) {
                throw ArithmeticException()
            }
            joinAll(job, deferred)
            println("hhaha")
        }
    }

    @Test
    fun test3() {
        runBlocking {
            val job = launch {
                val child = launch {
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        println("Child is cancelled")
                    }
                }
                yield()
                println("Cancelling child")
                child.cancel()
                child.join()
                yield()
                println("parent is not cancelled")
            }
            job.join()
        }
    }

    @Test
    fun test4() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) {
                launch {
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        withContext(NonCancellable) {
                            println("Children are cancelled")
                            delay(100)
                            println("The first child finished is non cancellable")
                        }
                    }
                }
                launch {
                    delay(10)
                    println("Second child throw an exception")
                    throw ArithmeticException()
                }
            }
            job.join()
        }
    }


    @Test
    fun test5() {
        runBlocking {
            val superVisor = SupervisorJob()
            with(CoroutineScope(coroutineContext + superVisor)) {
                val first = launch(CoroutineExceptionHandler{_, exception -> println(exception) }) {
                    println("the first job is failing")
                    throw AssertionError("first is canceled")
                }
                val second = launch {
                    first.join()
                    println("the first is finished")
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        println("the parent is canceled")
                    }
                }
                first.join()
                println("Cancelling the supervisor")
                superVisor.cancel()
//                second.join()
                println("hhha")
            }
        }
    }

    @Test
    fun test6() {
        //sampleStart
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            launch(handler) {
                supervisorScope {
                    val child = launch {
                        println("The child throws an exception")
                        throw AssertionError()
                    }
                    println("The scope is completing")
                }
            }
            println("The scope is completed")
        }
//sampleEnd

    }











}