package sideproject.mercy.utils

import sideproject.mercy.utils.NumberSymbolUtil.SymbolUnit.KoreaSymbol

object NumberSymbolUtil {

    private const val THOUSAND = 1000
    private const val TEN_THOUSAND = 10000

    /**
     * 숫자 단위에 해당하는 심볼 텍스트 설정
     * 이후에 같은 형식의 다른 형태의 text가 필요하다면 enum type 추가해서 파라미터로 사용
     *
     * @param thousand 천 단위
     * @param tenThousand 만 단위
     * @param million 100만 이상
     */
    enum class SymbolUnit(val thousand: String, val tenThousand: String, val million: String) {
        KoreaSymbol("천", "만", "100만+"),
    }

    /**
     * 숫자에 해당하는 심볼 단위로 변환
     *
     * @param number 변환 하려는 숫자
     * @param symbolUnit 심볼 텍스트 enum
     */
    fun convertNumber(number: Int, symbolUnit: SymbolUnit = KoreaSymbol): String {
        if (number < 0) return "0"

        return when {
            number in 1000..9999 -> {
                val quotient = number / THOUSAND
                val remainder = number % THOUSAND
                val decimalPoint = if (remainder >= 100) {
                    remainder / 100
                } else {
                    null
                }

                val decimalPointString = decimalPoint?.let {
                    ".$it"
                } ?: ""

                "$quotient$decimalPointString${symbolUnit.thousand}"
            }

            number in 10000..999999 -> {
                val quotient = number / TEN_THOUSAND
                val remainder = number % TEN_THOUSAND
                val decimalPoint = if (remainder >= THOUSAND) {
                    remainder / THOUSAND
                } else {
                    null
                }

                val decimalPointString = decimalPoint?.let {
                    ".$it"
                } ?: ""

                "$quotient$decimalPointString${symbolUnit.tenThousand}"
            }

            number >= 1000000 -> {
                symbolUnit.million
            }

            else -> number.toString()
        }
    }

    fun showSymbolUnit(number: Int) = number >= THOUSAND
}