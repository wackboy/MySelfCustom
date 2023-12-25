package com.example.myselfcustom.reflect

import java.lang.annotation.Inherited

open class Car<K, V> {
    private val carDesign = "设计稿"
    private val engine = "发动机"

    fun run(km: Long) {
        println("Car run $km km")
    }
}


interface ICar {
    fun combine()
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomAnnotation3(
    val value: Int
){

}

@Target(AnnotationTarget.CLASS)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomAnnotation2 {

}

@Target(AnnotationTarget.CLASS)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomAnnotation1(
    val value: Int
) {

}

