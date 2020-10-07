package com.peter.dovememo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.peter.dovememo.db.AppDatabase
import com.peter.dovememo.db.table.memo.Memo
import com.peter.dovememo.db.table.memo.MemoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//컨스트럭트는 생성자
class AppRepository private constructor(application: Application){

    private val memoDao: MemoDao

    init {
        val appDatabase = AppDatabase.getInstance(application)
        memoDao = appDatabase.memoDao()
    }
    //static 싱글톤
    companion object{
        private lateinit var instance:AppRepository

        fun getInstance(application: Application):AppRepository {
            if (!::instance.isInitialized){
                instance = AppRepository(application)
            }
            return instance
        }
    }

    fun getAllMemoList():LiveData<List<Memo>> = memoDao.getAll()

    //코루틴. 비동기를위해 기다릴수있음
    suspend fun deleteMemo(memo: Memo){
        // IO스레드에서 동작하겠다.
        withContext(Dispatchers.IO){
            memoDao.delete(memo)
        }
    }

    //코루틴. 비동기를위해 기다릴수있음
    suspend fun insertMemo(memo: Memo){
        // IO스레드에서 동작하겠다.
        withContext(Dispatchers.IO){
            memoDao.insert(memo)
        }
    }

}