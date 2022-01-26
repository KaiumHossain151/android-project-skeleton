package ai.retail.myapp.viewmodels.datamodels

import ai.retail.myapp.utils.Constants
import android.os.Parcel
import android.os.Parcelable

class CalendarTabModel() : Parcelable{

        var title: String = Constants.EMPTY_STRING
        var day: String = Constants.EMPTY_STRING
        var date: String = Constants.EMPTY_STRING
        var month: String = Constants.EMPTY_STRING
        var year: String = Constants.EMPTY_STRING
        var count: String = Constants.EMPTY_STRING
        var isSelected: Boolean = false

        constructor(day: String?, title: String?, date: String?, month: String?, year: String?) : this() {
                this.title = title!!
                this.date = date!!
                this.month = month!!
                this.year = year!!
                this.day = day!!
        }

        constructor(parcel: Parcel) : this() {
                title = parcel.readString().toString()
                day = parcel.readString().toString()
                date = parcel.readString().toString()
                month = parcel.readString().toString()
                year = parcel.readString().toString()
                count = parcel.readString().toString()
                isSelected = parcel.readByte() != 0.toByte()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(title)
                parcel.writeString(day)
                parcel.writeString(date)
                parcel.writeString(month)
                parcel.writeString(year)
                parcel.writeString(count)
                parcel.writeByte(if (isSelected) 1 else 0)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<CalendarTabModel> {
                override fun createFromParcel(parcel: Parcel): CalendarTabModel {
                        return CalendarTabModel(parcel)
                }

                override fun newArray(size: Int): Array<CalendarTabModel?> {
                        return arrayOfNulls(size)
                }
        }
}
