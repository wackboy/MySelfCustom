package com.example.myselfcustom.coroutinetest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineChannelTest {

    @Test
    fun test1() {
        runBlocking {
            val channel = Channel<Int>()
            launch {
                for (i in 1..5) {
//                    if (i == 3) channel.close()
                    channel.send(i * i)
                }
            }
            repeat(5) {
                val ret = channel.receive()
                println(ret)
            }
        }
    }

    @Test
    fun test2() {
        runBlocking {
            val numbers = produceNumbers()
            val square = square(numbers)
            repeat(5) {
                println(square.receive())
            }
            println("Done")
            coroutineContext.cancelChildren()
        }
    }

    @Test
    fun test3() {
        runBlocking {
            val producer = produceNumbers()
            repeat(5) {
                launchProcessor(it, producer)
            }
            delay(950)
            producer.cancel()   // 取消通道生产者 关闭通道
        }
    }

    @Test
    fun test4() {
        runBlocking {
            val channel = Channel<Int>(4)
            val launch = launch {
                repeat(10) {
                    println("Sending: $it")
                    channel.send(it)
                }
            }

            val ret = channel.receive()
            println("Receive is $ret")
            delay(1000)
            launch.cancel()
            println("Done!")
        }
    }

    fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
        for (msg in channel) {
            println("id is $id, ret is: $msg")
        }
    }

    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) {
            send(x++)
            delay(100)
        }
    }

    fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> {
        return produce {
            for (x in numbers) send(x * x)
        }
    }

}