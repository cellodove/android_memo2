package com.peter.dovememo.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.peter.dovememo.db.table.memo.Memo
import com.peter.dovememo.repository.AppRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    enum class Frag(val position : Int){
        LIST(0),VIEWER(1),EDITOR(2)
    }

    private val _fragLiveData = MutableLiveData<Frag>()
    val fragLiveData : LiveData<Frag>
        get() = _fragLiveData

    init {
        _fragLiveData.value = Frag.LIST
    }

    //레파지토리 연결
    private val repository : AppRepository by lazy {
        AppRepository.getInstance(getApplication())
    }
    //메모리스트 불러오기
    val memoListLiveData : LiveData<List<Memo>> by lazy {
        repository.getAllMemoList()
    }
    //코루틴사용 메모입력하기 비동기를 사용하기위해 코루틴사용
    fun insertMemo(memo: Memo){
        viewModelScope.launch {
            repository.insertMemo(memo)
        }
    }
    //코루틴 메모삭제
    fun deleteMemo(position: Int){
        viewModelScope.launch {
            //해당포지션에있는 메모를 변수에입력
            val memo = memoListLiveData.value?.get(position)
            //포지션이 0이 아니라면 삭제한다.
            if (memo != null){
                repository.deleteMemo(memo)
            }
        }
    }

}