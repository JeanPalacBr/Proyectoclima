package com.example.recycledview.data

import android.os.Parcel
import android.os.Parcelable


data class Forecasts (val cityname: String?, val temp: Double, val temp_max: Double, val temp_min: Double, val Humidity: Double, val Feels_like: Double, val Pressure: Double, val Description: String?, val IconID: String?, val WindSpeeed: Double, val datehour: String?): Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cityname)
        parcel.writeDouble(temp)
        parcel.writeDouble(temp_max)
        parcel.writeDouble(temp_min)
        parcel.writeDouble(Humidity)
        parcel.writeDouble(Feels_like)
        parcel.writeDouble(Pressure)
        parcel.writeString(Description)
        parcel.writeString(IconID)
        parcel.writeDouble(WindSpeeed)
        parcel.writeString(datehour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Forecasts> {
        override fun createFromParcel(parcel: Parcel): Forecasts {
            return Forecasts(parcel)
        }

        override fun newArray(size: Int): Array<Forecasts?> {
            return arrayOfNulls(size)
        }
    }

}