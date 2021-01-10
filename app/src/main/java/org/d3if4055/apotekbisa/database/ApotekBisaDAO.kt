package org.d3if4055.apotekbisa.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ApotekBisaDAO {

    @Insert
    fun insert(apotekBisa: ApotekBisa)

    @Update
    fun update(apotekBisa: ApotekBisa)

    @Query("SELECT * FROM apotekbisa")
    fun getApotekBisa(): LiveData<List<ApotekBisa>>

    @Query("DELETE FROM apotekbisa")
    fun clear()

    @Query("DELETE FROM apotekbisa WHERE id = :ApotekID")
    fun delete(ApotekID: Long)

}