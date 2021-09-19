package com.fabulouszanna.fabpokedex.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.R
import com.fabulouszanna.fabpokedex.adapters.PokemonDetailsViewPagerAdapter
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsBinding
import com.fabulouszanna.fabpokedex.other.extractColorResourceFromType
import com.fabulouszanna.fabpokedex.other.retrieveDrawableFromName
import com.fabulouszanna.fabpokedex.ui.viewmodels.PokemonViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailsBinding
    private val viewModel: PokemonViewModel by viewModels()
    private val args: PokemonDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPokemonDetailsBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
        setupViewPager()
        spinPokeBall()
    }

    private fun changeStatusBarColor(color: Int) {
        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = color
        }
    }

    private fun populateView() {
        viewModel.getPokemonById(args.id).observe(viewLifecycleOwner) { pokemon ->
            val color = extractColorResourceFromType(requireContext(), pokemon.types[0])
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
                        extractColorResourceFromType(requireContext(), pokemon.types[1])
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

    private fun setupViewPager() {
        val viewPagerAdapter = PokemonDetailsViewPagerAdapter(this, args.id)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "About"
                1 -> "Evolutions"
                else -> "Fragment"
            }
        }.attach()
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