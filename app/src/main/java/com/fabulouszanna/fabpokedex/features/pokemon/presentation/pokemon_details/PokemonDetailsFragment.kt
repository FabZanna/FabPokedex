package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.core.util.retrieveColorResourceFromType
import com.fabulouszanna.fabpokedex.core.util.retrieveDrawableFromName
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsBinding
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsBinding
    private val viewModel: PokemonDetailsViewModel by viewModels()
    private val args: PokemonDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
        spinPokeBall()
    }

    private fun changeStatusBarColor(color: Int) {
        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = color
        }
    }

    private fun populateView() {
        viewModel.updateId(args.id)
        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            setupViewPager(pokemon)
            val color = retrieveColorResourceFromType(requireContext(), pokemon.types[0])
            changeStatusBarColor(color)
            setTabsColor(color)

            binding.apply {
                root.setBackgroundColor(color)
                pokemonName.text = pokemon.name
                pokemonId.text = pokemon.pokemonId

                val img = retrieveDrawableFromName(requireContext(), pokemon.imgUrl)
                glide.load(img).into(pokemonImg)

                populateType(firstType, pokemon.types[0])
                if (pokemon.types.size > 1) {
                    val secondTypeColor =
                        retrieveColorResourceFromType(requireContext(), pokemon.types[1])
                    populateType(secondType, pokemon.types[1])
                    (secondType.background as GradientDrawable).run {
                        mutate()
                        setColor(secondTypeColor)
                    }
                    secondType.visibility = View.VISIBLE
                }


            }
        }
    }

    private fun populateType(layout: LinearLayout, type: String) {
        val imgView = layout.getChildAt(0) as ImageView
        val textView = layout.getChildAt(1) as TextView
        val typeImage = retrieveDrawableFromName(requireContext(), type.lowercase())

        glide.load(typeImage).into(imgView)
        textView.text = type
    }

    private fun setupViewPager(pokemon: Pokemon) {
        binding.viewPager.adapter = PokemonDetailsViewPagerAdapter(this, pokemon, onPokemonClicked = ::onEvolutionPokemonClicked)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "About"
                1 -> "Evolutions"
                2 -> "Moves"
                else -> "Fragment"
            }
        }.attach()
    }

    private fun onEvolutionPokemonClicked(id: String) {
        viewModel.updateId(id)
    }

    private fun setTabsColor(color: Int) {
        binding.tabLayout.apply {
            setSelectedTabIndicatorColor(color)
            setTabTextColors(tabTextColors?.defaultColor ?: Color.RED, color)
        }
    }

    private fun spinPokeBall() {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 5000
        rotate.repeatCount = Animation.INFINITE
        rotate.interpolator = LinearInterpolator()
        binding.pokeball.animation = rotate
    }
}