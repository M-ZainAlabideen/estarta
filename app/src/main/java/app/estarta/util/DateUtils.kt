package app.estarta.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun convertToLocalTime(dateValue: String?): String {
            if (dateValue != null) {
                val date: Date? =
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(dateValue)
                val localFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault())
                localFormatter.timeZone = TimeZone.getDefault()
                return localFormatter.format(date?.time)
            }
            return ""
        }
    }
}