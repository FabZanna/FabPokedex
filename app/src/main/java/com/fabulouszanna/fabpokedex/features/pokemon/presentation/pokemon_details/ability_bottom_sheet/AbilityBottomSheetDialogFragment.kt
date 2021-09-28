package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details.ability_bottom_sheet

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fabulouszanna.fabpokedex.databinding.BottomSheetAbilityBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbilityBottomSheetDialogFragment(private val abilityName: String, private val color: Int) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetAbilityBinding
    private val viewModel: AbilityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        BottomSheetAbilityBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
    }

    private fun populateView() {
        viewModel.getAbilityByName(abilityName).observe(viewLifecycleOwner) { ability ->
            binding.apply {
                abilityName.backgroundTintList = ColorStateList.valueOf(color)
                abilityName.text = ability.name

                gameDescription.text = ability.gameText
                inDepthEffect.text = ability.inDepthEffect
            }
        }
    }
}