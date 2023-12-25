package com.example.myselfcustom.reflect

import org.junit.Test
import java.lang.reflect.ParameterizedType

@CustomAnnotation1(43)
@CustomAnnotation2
class Benz(
    var carName: String = "奔驰",
    var carColor: String = "白色"
) : Car<String, Int>(), ICar {

    @CustomAnnotation3(value = 777)
    override fun combine() {
        println("组装一台奔驰")
        runTimeAnno()
    }

    @CustomAnnotation3(value = 888)
    fun runTimeAnno() {
        val ret = processRunTimeAnnotation("runTimeAnno") as Int
        println(ret)
    }

    private fun processRunTimeAnnotation(methodName: String): Int {
        val clazz = Benz::class.java
        val methods = clazz.declaredMethods
        for (method in methods) {
            if (method.name == methodName && method.isAnnotationPresent(CustomAnnotation3::class.java)) {
                val value = method.getAnnotation(CustomAnnotation3::class.java)?.value
                return value ?: 0
            }
        }
        return 0

    }

    private fun privateMethod(some: String = "hello") {
        println("我是私有方法: $some")
    }

    class BenzMini() {

    }
}

class TestReflect {

    @Test
    fun test1() {
        val benz = Benz()
        val benzClazz = benz::class.java
        println(benzClazz.name)
        println(benzClazz.superclass)
        val clazzs = benzClazz.interfaces
        for (clazz in clazzs) {
            println(clazz.name)
        }
    }

    @Test
    fun test2() {
        val benzClass = Benz()::class.java
        val constructor = benzClass.getDeclaredConstructor(String::class.java, String::class.java)
        val fields = benzClass.fields
        for (field in fields) {
            println(field)
        }
        val benz = constructor.newInstance("白色", "比亚迪")
        benz.carColor = "黑色"
        benz.carName = "梅赛德斯"
        benz.combine()
        println(benz.carName)
    }

    @Test
    fun test3() {
        val benz = Benz()
        val benzClass = Benz::class.java
        val privateMethod = benzClass.getDeclaredMethod("privateMethod", String::class.java)
        privateMethod.isAccessible = true
        privateMethod.invoke(benz, "接受的参数")
    }

    @Test
    fun test4() {
        val benzClazz = Benz::class.java
        val constructor = benzClazz.declaredConstructors[2]
        val benz = constructor.newInstance("haha", "sss") as Benz
        println(benz.carName)
    }

    /**
     * 获取父类的泛型
     */
    @Test
    fun test5() {
        val benz = Benz("hah", "ss")
        val genericType = benz::class.java.genericSuperclass
        (genericType as ParameterizedType).also {
            val actualType = genericType.actualTypeArguments
            for (type in actualType) {
                println(type.typeName)
            }
        }
    }

    /**
     * 获取注解
     */
    @Test
    fun test6() {
        val benz = Benz::class.java
        val annotations = benz.annotations
        val annotation1 = benz.getAnnotation(CustomAnnotation1::class.java)
        println(annotation1.annotationClass.simpleName)
        annotation1.annotationClass.constructors
        for (annotation in annotations) {
            println((annotation as Annotation).annotationClass.simpleName)
        }
    }

    @Test
    fun test7() {
        val benz = Benz::class.java
        val combineMethod = benz.getDeclaredMethod("combine")
        var declaredAnnotationsByType =
            combineMethod.getDeclaredAnnotation(CustomAnnotation3::class.java)
        println(declaredAnnotationsByType.value)
        val o = benz.getConstructor().newInstance()
        combineMethod.invoke(o)
    }
}










