package com.example.myselfcustom

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testComparator() {
        val list = mutableListOf(
            Meta(0, "item-2"),
            Meta(0, "item-1"),
            Meta(1, "item0"),
            Meta(0, "item1"),
            Meta(1, "item2"),
            Meta(0, "item3"),
            Meta(0, "item4"),
            Meta(1, "specialA"),
            Meta(2, "specialB"),
            Meta(3, "specialC"),
            Meta(2, "specialD"),
            Meta(2, "specialE"),
            Meta(2, "specialF"),
        )
        // 内部是一个归并排序的规则
        list.sortWith { o1, o2 ->
            val data = if (o1.data > 0 && o2.data > 0) {
                if (o1.data == 2 && o2.data == 2) {
                    o1.tag.compareTo(o2.tag)
                }
                else if (o1.data == 2) -1
                else if (o2.data == 2) 1
                else 0
            } else {
                0
            }
            println("o1.tag: ${o1.tag}  --  o2.tag: ${o2.tag}  --  ret: $data")
            data
        }

        list.forEach {
            println(it)
        }

    }

    @Test
    fun test2() {
        val list = mutableListOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3)

        list.sortWith { o1, o2 ->
            // 不稳定比较器，比较结果与元素的顺序有关
            // 当元素相等时，可能交换它们的相对位置
            if (o1 == o2) 0
            else if (o1 > o2) -1
            else 1
        }

        println(list)
    }

}
data class Meta(
    val data: Int,
    val tag: String
)
