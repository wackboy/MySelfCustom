package com.example.myselfcustom.sourcecodelearn.apt

class Benz(
    val carName: String = "奔驰",
    val carColor: String = "白色"
) : Car<String, Int>(), ICar {

    override fun combine() {
        TODO("Not yet implemented")
    }

    private fun privateMethod() {
        println("我是私有方法")
    }

}
