package sideproject.mercy.utils.extensions

fun Boolean?.orFalse(): Boolean = this ?: false

fun Long?.orZero(): Long = this ?: 0L
fun Double?.orZero(): Double = this ?: 0.0
fun Int?.orZero(): Int = this ?: 0
fun Float?.orZero(): Float = this ?: 0.0f

fun Long?.orDefault(default: Long): Long = this ?: default
fun Double?.orDefault(default: Double): Double = this ?: default
fun Int?.orDefault(default: Int): Int = this ?: default
fun Float?.orDefault(default: Float): Float = this ?: default