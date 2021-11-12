package ir.reservs.reservs.model

data class User(var name: String? = null, var phone: String, var image: String?,
                var token: String, var is_verify: Boolean, var birthday: String?, var credit: Long) {

    override fun toString(): String {
        return "\nname:$name\nphone:$phone\nimage:$image\ntoken:$token\nis_verify:$is_verify\nbirthday:$birthday \ncredit:$credit"
    }
}