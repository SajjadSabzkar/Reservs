package ir.reservs.reservs.ui.main.times

import ir.reservs.reservs.model.ReserveHistory
import ir.reservs.reservs.model.Time

interface OnClickListener {
    fun click(reserve: Time)
}