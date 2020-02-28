package ir.reservs.reservs.model

data class User(var name: String, var phone: String, var image: String,
                var token: String, var is_verify: Boolean)