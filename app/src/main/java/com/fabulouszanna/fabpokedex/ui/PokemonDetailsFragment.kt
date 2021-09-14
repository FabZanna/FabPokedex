package com.fabulouszanna.fabpokedex.ui

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.*
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.R
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonDetailsBinding
import com.fabulouszanna.fabpokedex.other.extractColorResourceFromType
import com.fabulouszanna.fabpokedex.other.retrieveDrawableFromName
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
    ): View = FragmentPokemonDetailsBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
        attachAboutFragment()
    }

    private fun populateView() {
        viewModel.getPokemonById(args.id).observe(viewLifecycleOwner) { pokemon ->
            binding.apply {

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
                root.setBackgroundColor(
                    extractColorResourceFromType(
                        requireContext(),
                        pokemon.types[0]
                    )
                )


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

    private fun attachAboutFragment() {
        childFragmentManager.commitNow {
            replace(R.id.contentContainer, PokemonDetailsAboutFragment(args.id))
        }

    }
}