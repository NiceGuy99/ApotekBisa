package org.d3if4055.apotekbisa.ui.home

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.*
import org.d3if4055.apotekbisa.database.ApotekBisa
import org.d3if4055.apotekbisa.database.ApotekBisaDAO

class ApotekBisaViewModel(
    val database: ApotekBisaDAO,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope =   CoroutineScope(Dispatchers.Main + viewModelJob)
    val apotekBisa = database.getApotekBisa()

    fun onClickInsert(apotekBisa: ApotekBisa) {
        uiScope.launch {
            insert(apotekBisa)
        }
    }

    private suspend fun insert(apotekBisa: ApotekBisa) {
        withContext(Dispatchers.IO) {
            database.insert(apotekBisa)
        }
    }

    fun onClickUpdate(apotekBisa: ApotekBisa) {
        uiScope.launch {
            update(apotekBisa)
        }
    }

    private suspend fun update(apotekBisa: ApotekBisa) {
        withContext(Dispatchers.IO) {
            database.update(apotekBisa)
        }
    }

    fun onClickClear() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onClickDelete(id: Long) {
        uiScope.launch {
            delete(id)
        }
    }

    private suspend fun delete(id: Long) {
        withContext(Dispatchers.IO) {
            database.delete(id)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory (
        private val dataSource: ApotekBisaDAO,
        private val application: Application
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ApotekBisaViewModel::class.java)) {
                return ApotekBisaViewModel(
                    dataSource,
                    application
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

        }

    }
}