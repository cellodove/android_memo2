package com.peter.dovememo.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.peter.dovememo.db.table.memo.Memo
import com.peter.dovememo.db.table.memo.MemoDao


@Database(entities = [Memo::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    //스태틱 싱클톤 패턴
    companion object{
        private lateinit var instance: AppDatabase

        fun getInstance(application: Application): AppDatabase{
            if (!::instance.isInitialized){
                instance =
                    Room.databaseBuilder(
                        application,
                        AppDatabase::class.java,
                        "memo-db"
                    ).build()
            }
            return instance
        }

    }
    //memoDAO 를 가지고있는다.연결할수있도록
    abstract fun memoDao(): MemoDao

}