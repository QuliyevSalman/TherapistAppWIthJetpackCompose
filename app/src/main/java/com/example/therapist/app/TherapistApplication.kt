package com.example.therapist.app

import android.app.Application
import androidx.room.Room
import com.example.therapist.data.db.TherapistAppDatabase
const val db_name = "therapist_local_db"
class TherapistApplication: Application() {
    val db: TherapistAppDatabase by lazy {
        Room.databaseBuilder(
            this,
            TherapistAppDatabase::class.java,
            db_name
        ).build()
    }
    override fun onCreate() {
        super.onCreate()
    }
}
