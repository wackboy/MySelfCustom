package com.example.myselfcustom.sourcecodelearn.apt

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

@Target(AnnotationTarget.TYPE)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomAnnotation3 {

}

@Target(AnnotationTarget.TYPE)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomAnnotation2 {

}

@Target(AnnotationTarget.TYPE)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomAnnotation1 {

}