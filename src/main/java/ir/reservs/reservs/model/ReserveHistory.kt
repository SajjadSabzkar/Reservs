package ir.reservs.reservs.model

data class ReserveHistory(var name: String,
                          var location: String,
                          var date: String,
                          var startTime: String,
                          var endTime: String,
                          var status: Int)