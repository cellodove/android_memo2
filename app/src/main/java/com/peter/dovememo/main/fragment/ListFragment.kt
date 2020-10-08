package com.peter.dovememo.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.peter.dovememo.R
import com.peter.dovememo.databinding.FragmentListBinding
import com.peter.dovememo.db.table.memo.Memo
import com.peter.dovememo.main.MainViewModel
import com.peter.dovememo.main.fragment.list.MemoAdapter

class ListFragment : Fragment(), MemoAdapter.MemoListListener {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    //함수를 만들어두고 사용을안했음
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initRecyclerView()
        setObserver()
        setListener()
    }

    //초기화를 해주지않음
    private fun initRecyclerView() {
        binding.list.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

            adapter = MemoAdapter(this@ListFragment)
        }
    }

    private fun setObserver() {
        viewModel.memoListLiveData.observe(requireActivity(), Observer {
            (binding.list.adapter as MemoAdapter).submitList(it)
        })
    }

    private fun setListener() {
        binding.apply {
            add.setOnClickListener {
                val title = inputTitel.text.toString()

                if (title.isNotEmpty()) {
                    val memo = Memo(title = title)

                    viewModel.insertMemo(memo)

                    inputTitel.text.clear()
                }
            }
        }
    }

    override fun onMemoItemClick(position: Int) {
        Toast.makeText(requireContext(), "on $position item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onMemoItemLongClick(position: Int) {
        viewModel.deleteMemo(position)
    }
}