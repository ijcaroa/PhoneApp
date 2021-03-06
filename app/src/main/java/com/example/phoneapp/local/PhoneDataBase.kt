package com.example.phoneapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [PhoneListEntity::class,PhoneDetailEntity::class],version = 1)
abstract class PhoneDataBase : RoomDatabase(){
    abstract fun phoneDao(): PhoneDao
    abstract fun detailsDao(): DetailsDao
    companion object {
        @Volatile
        private var INSTANCE: PhoneDataBase? = null

        fun getDatabase(context: Context): PhoneDataBase {
            val tempInstance = INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDataBase::class.java,
                    "phone_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

