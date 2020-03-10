package com.example.recycledview.data

import android.os.Parcel
import android.os.Parcelable


data class User (val name: String?, val lastname: String?,val Email: String?, val phone: String?, val imgsrc: String?): Parcelable
     {
         constructor(parcel: Parcel) : this(
             parcel.readString(),
             parcel.readString(),
             parcel.readString(),
             parcel.readString(),
             parcel.readString()
         ) {
         }

         override fun writeToParcel(parcel: Parcel, flags: Int) {
             parcel.writeString(name)
             parcel.writeString(lastname)
             parcel.writeString(Email)
             parcel.writeString(phone)
             parcel.writeString(imgsrc)
         }

         override fun describeContents(): Int {
             return 0
         }

         companion object CREATOR : Parcelable.Creator<User> {
             override fun createFromParcel(parcel: Parcel): User {
                 return User(parcel)
             }

             override fun newArray(size: Int): Array<User?> {
                 return arrayOfNulls(size)
             }
         }

     }