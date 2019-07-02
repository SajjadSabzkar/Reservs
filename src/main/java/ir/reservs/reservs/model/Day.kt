package ir.reservs.reservs.model

data class Day(val num: Int, val name: String) {
    fun equals(other: Day): Boolean {
        if (other.name == name && other.num == num) {
            return true
        }
        return false
    }
}