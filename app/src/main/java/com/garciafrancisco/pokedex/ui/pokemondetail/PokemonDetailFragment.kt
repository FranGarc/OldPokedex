package com.garciafrancisco.pokedex.ui.pokemondetail

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.garciafrancisco.pokedex.R
import com.garciafrancisco.pokedex.data.models.toPokemonData
import com.garciafrancisco.pokedex.data.remote.responses.Pokemon
import com.garciafrancisco.pokedex.databinding.FragmentPokemonDetailBinding
import com.garciafrancisco.pokedex.repository.PokedexRepository
import com.garciafrancisco.pokedex.ui.custom.Type
import com.garciafrancisco.pokedex.ui.custom.getType
import com.garciafrancisco.pokedex.ui.pokemonlist.PokemonListFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [PokemonListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */

private const val TAG = "PokemonDetailFragment"

class PokemonDetailFragment : Fragment() {

    lateinit var viewModel: PokemonDetailViewModel

    /**
     * The placeholder content this fragment is presenting.
     */

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentPokemonDetailBinding? = null

    lateinit var selectedPokemonId: String

    lateinit var pokemon: Pokemon

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")

        arguments?.let {
            if (it.containsKey(ARG_POKEMON_ID)) {
                selectedPokemonId = it.getString(ARG_POKEMON_ID).toString()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")
        val viewModelProviderFactory = PokemonDetailViewModelProviderFactory(PokedexRepository())
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[PokemonDetailViewModel::class.java]

        viewModel.loadPokemonData(selectedPokemonId)
        setObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        val rootView = binding.root
        toolbarLayout = binding.toolbarLayout
        binding.apply {
            tvPokemonName?.text = ""
            fab?.setOnClickListener {
                binding.pokemonInfoContainer?.let { container ->
                    pokemon.let { pokemon ->
                        Snackbar.make(container, "${pokemon.name} CAPTURED", Snackbar.LENGTH_LONG).show()
                    }

                }
            }
        }
        rootView.setOnDragListener(dragListener)
        return rootView
    }

    private fun setObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            Log.d(TAG, "isLoading changed to ($isLoading)")
            toggleLoading(isLoading)
        }

        viewModel.pokemonData.observe(viewLifecycleOwner) { pokemonResponse ->
            Log.d(TAG, "pokemonData changed to ($pokemonResponse)")
            pokemon = pokemonResponse
            renderPokemonResponse(pokemonResponse)

        }

        viewModel.loadError.observe(viewLifecycleOwner) { pokemonError ->

            if (pokemonError.isNotEmpty() && pokemonError.isNotBlank()) {
                Log.e(TAG, "pokemonDetailError received: $pokemonError")

                binding.pokemonInfoContainer?.let {
                    Snackbar.make(it, "Error: $pokemonError", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderPokemonResponse(pokemonResponse: Pokemon) {
        Log.d(TAG, "renderPokemonResponse()")

        binding.ivPokemonImage?.let {
            Glide.with(this).load(pokemonResponse.sprites.front_default).into(it)
        }
        val types = pokemonResponse.types
        val pokemonType1 = getType(types.first().type.name)
        setType1(pokemonType1)
        if (types.size > 1) {
            val pokemonType2 = getType(types[1].type.name)
            setType2(pokemonType2)
        }
        val pokemonData = pokemonResponse.toPokemonData()
        binding.tvPokemonName?.text = pokemonData.pokemonName

        binding.tvPokemonHeight?.text = resources.getString(
            R.string.pokemon_height,
            pokemonData.height
        )
        binding.tvPokemonWeight?.text = resources.getString(
            R.string.pokemon_weight,
            pokemonData.weight
        )
    }

    private fun setType1(pokemonType: Type) {
        binding.typeContainer1?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                pokemonType.color
            )
        )
        binding.ivTypeIcon1?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                pokemonType.icon
            )
        )
        binding.tvTypeName1?.apply {
            text = resources.getText(pokemonType.stringKey)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }

    private fun setType2(pokemonType: Type) {

        binding.typeContainer2?.apply {

            visibility = View.VISIBLE
            setBackgroundColor(ContextCompat.getColor(requireContext(), pokemonType.color))
        }
        binding.ivTypeIcon2?.setImageDrawable(ContextCompat.getDrawable(requireContext(), pokemonType.icon))
        binding.tvTypeName2?.apply {
            text = resources.getText(pokemonType.stringKey)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }


    }

    private fun toggleLoading(setTo: Boolean) {
        if (setTo) {
            showProgressBar()
        } else {
            hideProgressBar()
        }
    }

    private fun showProgressBar() {
        binding.pbLoading?.let {
            it.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar() {
        binding.pbLoading?.let {
            it.visibility = View.INVISIBLE
        }
    }


    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_POKEMON_ID = "pokeapi_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}