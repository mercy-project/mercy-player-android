package sideproject.mercy.shared.storage

interface BasePreference {
    fun put(key: String, value: String?)
    fun put(key: String, value: Boolean)
    fun put(key: String, value: Int)
    fun put(key: String, value: Long)

    fun getValue(key: String, defValue: String?): String?
    fun getValue(key: String, defValue: Int): Int
    fun getValue(key: String, defValue: Boolean): Boolean
    fun getValue(key: String, defValue: Long): Long

    fun remove(key: String)
}