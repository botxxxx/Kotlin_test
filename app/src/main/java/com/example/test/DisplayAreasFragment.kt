package com.example.test

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.example.test.adapters.*
import com.example.test.databinding.FragmentDisplayAreasViewBinding
import com.example.test.viewmodels.*
import dagger.hilt.android.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DisplayAreasFragment : Fragment(){

    private lateinit var binding: FragmentDisplayAreasViewBinding
    private var searchJob: Job? = null
    private val adapter = DisplayAreasAdapter()
    private val viewModel: DisplayAreasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDisplayAreasViewBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.areaList.adapter = adapter
        subscribeUi()

        return binding.root
    }

    private fun subscribeUi() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getResult().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}