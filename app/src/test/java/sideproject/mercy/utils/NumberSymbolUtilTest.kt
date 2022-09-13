package sideproject.mercy.utils

import org.junit.Assert.*
import org.junit.Test

class NumberSymbolUtilTest {

    @Test
    fun `1000 미만 단위는 심볼없이 자연수로 표시`() {
        val expected = "500"
        val actual = getConvertNumber(500)
        assertEquals(expected, actual)
    }

    @Test
    fun `양수가 아닌 숫자는 0으로 변환`() {
        val expected = "0"
        val actual = getConvertNumber(-1)
        assertEquals(expected, actual)
    }

    @Test
    fun `1,000 ~ 1,099 사이일때 소수점 없이 표시 `() {
        val expected = "1천"
        var actual = getConvertNumber(1000)
        assertEquals(expected, actual)

        actual = getConvertNumber(1001)
        assertEquals(expected, actual)

        actual = getConvertNumber(1099)
        assertEquals(expected, actual)
    }

    @Test
    fun `1,100 ~ 10,000 미만 단위 소수점 이하 1자리 표시`() {
        val expected = "1.1천"
        var actual = getConvertNumber(1100)
        assertEquals(expected, actual)

        actual = getConvertNumber(1155)
        assertEquals(expected, actual)
    }

    @Test
    fun `10,000 ~ 1000,000 미만 단위는 만 심볼 표시`() {
        val expected = "1만"
        val actual = getConvertNumber(10000)
        assertEquals(expected, actual)
    }

    @Test
    fun `10,000 단위 소수점 표시`() {
        val expected = "1.1만"
        val actual = getConvertNumber(11000)
        assertEquals(expected, actual)
    }

    @Test
    fun `100,000 ~ 1000,000 미만 단위 만 심볼 표시`() {
        val expected = "10만"
        val actual = getConvertNumber(100000)
        assertEquals(expected, actual)
    }

    @Test
    fun `100,000 단위 표시`() {
        val expected = "11만"
        val actual = getConvertNumber(110000)
        assertEquals(expected, actual)
    }

    @Test
    fun `100,000 단위 소수점 표시`() {
        val expected = "11.5만"
        val actual = getConvertNumber(115000)
        assertEquals(expected, actual)
    }

    @Test
    fun `100,000 단위 경계값 테스트`() {
        val expected = "99.9만"
        val actual = getConvertNumber(999999)
        assertEquals(expected, actual)
    }

    @Test
    fun `1,000,000 이상 단위는 100만+ 심볼 표시`() {
        val expected = "100만+"
        val actual = getConvertNumber(1000000)
        assertEquals(expected, actual)
    }

    private fun getConvertNumber(number: Int): String {
        return NumberSymbolUtil.convertNumber(number).also {
            println("converted value: $number -> $it")
        }
    }
}