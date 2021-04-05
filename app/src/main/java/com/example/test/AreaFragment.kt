package com.example.test

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.test.adapters.*
import com.example.test.data.area.AreaData
import com.example.test.data.area.ParcelableAreaData
import com.example.test.databinding.FragmentAreaViewBinding
import com.example.test.viewmodels.*
import dagger.hilt.android.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AreaFragment : Fragment(){

    private val args: AreaFragmentArgs by navArgs()
    private lateinit var binding: FragmentAreaViewBinding
    private var searchJob: Job? = null
    private val adapter = AreaAdapter()
    private val viewModel: AreaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAreaViewBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.setClickListener {
            activity?.onBackPressed()
        }

        binding.plantList.adapter = adapter
        subscribeUi(args.area)

        return binding.root
    }

    private fun subscribeUi(area: ParcelableAreaData) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            binding.area = area
            viewModel.getResult(area.E_Name).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}