package com.example.test

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.test.databinding.FragmentPlantViewBinding
import com.example.test.viewmodels.PlantViewModel
import dagger.hilt.android.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class PlantFragment : Fragment() {

    private val args: PlantFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlantViewBinding
    private val viewModel: PlantViewModel by viewModels()
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantViewBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.setClickListener {
            activity?.onBackPressed()
        }

        subscribeUi(args.plant)
        return binding.root
    }

    private fun subscribeUi(login: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            binding.plant = viewModel.getResult(login)
        }
    }
}