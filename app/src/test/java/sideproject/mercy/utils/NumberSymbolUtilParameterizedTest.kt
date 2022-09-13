package sideproject.mercy.utils

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class NumberSymbolUtilParameterizedTest(private val inputNumber: Int, private val expected: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0} -> {1}")
        fun data(): Collection<Array<Any>> {
            // 테스트 하고싶은 값을 추가
            return listOf(
                arrayOf(-1, "0"),
                arrayOf(-100, "0"),
                arrayOf(-1000, "0"),
                arrayOf(-10000, "0"),
                arrayOf(0, "0"),
                arrayOf(1, "1"),
                arrayOf(500, "500"),
                arrayOf(999, "999"),
                arrayOf(1000, "1천"),
                arrayOf(1099, "1천"),
                arrayOf(1100, "1.1천"),
                arrayOf(1199, "1.1천"),
                arrayOf(1200, "1.2천"),
                arrayOf(1299, "1.2천"),
                arrayOf(8888, "8.8천"),
                arrayOf(9999, "9.9천"),
                arrayOf(10000, "1만"),
                arrayOf(10999, "1만"),
                arrayOf(11999, "1.1만"),
                arrayOf(12000, "1.2만"),
                arrayOf(100000, "10만"),
                arrayOf(101999, "10.1만"),
                arrayOf(109999, "10.9만"),
                arrayOf(110000, "11만"),
                arrayOf(110001, "11만"),
                arrayOf(119999, "11.9만"),
                arrayOf(253499, "25.3만"),
                arrayOf(701000, "70.1만"),
                arrayOf(790900, "79만"),
                arrayOf(791000, "79.1만"),
                arrayOf(999999, "99.9만"),
                arrayOf(1000000, "100만+"),
                arrayOf(1000001, "100만+"),
                arrayOf(2000000, "100만+"),
                arrayOf(3000000, "100만+"),
                arrayOf(9000000, "100만+"),
                arrayOf(10000000, "100만+"),
                arrayOf(1000000000, "100만+"),
            )
        }
    }

    @Test
    fun `전체 테스트`() {
        assertEquals(expected, getConvertNumber(inputNumber))
    }

    private fun getConvertNumber(number: Int): String {
        return NumberSymbolUtil.convertNumber(number).also {
            println("converted value: $number -> $it")
        }
    }
}