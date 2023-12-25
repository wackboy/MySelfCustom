package com.example.myselfcustom.reflect

import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class Business1 {

    fun doBusiness1() {
        println("复杂业务1")
    }
}

class Business2 {

    fun doBusiness2() {
        println("复杂业务2")
    }
}

class Client {
    @Test
    fun test() {
        Business1().doBusiness1()
    }

    @Test
    fun test2ByReflect() {
        val file = File("/Users/wackyboy/Documents/code/Android/customView/MySelfCustom/app/src/test/java/com/example/myselfcustom/reflect/config")
        val config = Properties()
        config.load(FileInputStream(file))
        val classPath = config["class"] as String
        val methodPath = config["method"] as String
        // 生成clas
        val clazz = Class.forName(classPath)
        val constructor = clazz.getDeclaredConstructor()
        // 生成实例
        val o = constructor.newInstance()
        // 获得方法
        val method = clazz.getDeclaredMethod(methodPath)
        method.invoke(o)
    }
}