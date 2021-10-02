package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list.filter_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.fabulouszanna.fabpokedex.R
import com.fabulouszanna.fabpokedex.core.util.RecyclerViewGridLayoutSpace
import com.fabulouszanna.fabpokedex.databinding.BottomSheetFilterByTypeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TypeFilterBottomSheetDialogFragment(private val onTypeClicked: (type: String) -> Unit) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetFilterByTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFilterByTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val types = resources.getStringArray(R.array.types).toList()
        val typeAdapter = TypeFilterAdapter(types) {
            onTypeClicked(it)
            lifecycleScope.launch {
                delay(300)
                dismiss()
            }
        }

        binding.types.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = typeAdapter
            addItemDecoration(RecyclerViewGridLayoutSpace(includeEdge = true, spacing = 16))
        }
    }
}