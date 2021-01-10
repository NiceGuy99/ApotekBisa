package org.d3if4055.apotekbisa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ApotekBisa::class], version = 1, exportSchema = false)
abstract class ApotekBisaDatabase : RoomDatabase() {

    abstract val apotekBisaDAO: ApotekBisaDAO

    companion object {
        const val DATABASE_NAME = "apotekbisa_database"
        @Volatile
        private var INSTANCE: ApotekBisaDatabase? = null

        fun getInstance(context: Context) : ApotekBisaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ApotekBisaDatabase::class.java,
                            DATABASE_NAME
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}