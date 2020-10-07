package com.peter.dovememo.db.table.memo

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.peter.dovememo.db.table.BaseDao

interface MemoDao : BaseDao<Memo> {
    @Query("SELECT * FROM memo ORDER BY date, id DESC")
    fun getAll(): LiveData<List<Memo>>
}
