package com.garciafrancisco.pokedex.ui.pokemonlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.garciafrancisco.pokedex.R
import com.garciafrancisco.pokedex.databinding.FragmentPokemonListBinding
import com.garciafrancisco.pokedex.repository.PokedexRepository
import com.garciafrancisco.pokedex.ui.pokemondetail.PokemonDetailFragment.Companion.ARG_POKEMON_ID
import com.google.android.material.snackbar.Snackbar

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
private const val TAG = "PokemonListFragment"

class PokemonListFragment : Fragment() {


    lateinit var viewModel: PokemonListViewModel
    lateinit var pokemonListAdapter: PokemonListAdapter

    private var _binding: FragmentPokemonListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")

        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")


        val viewModelProviderFactory = PokemonListViewModelProviderFactory(PokedexRepository())

        viewModel =
            ViewModelProvider(this, viewModelProviderFactory)[PokemonListViewModel::class.java]

        setupRecyclerView()


        pokemonListAdapter.setOnItemClickListener { pokeApiId ->
            Log.d(
                TAG,
                "OnItemClickListener(): $pokeApiId"
            )

            val bundle = Bundle().apply {
                putSerializable(ARG_POKEMON_ID, pokeApiId.toString())
            }
            findNavController().navigate(R.id.list_to_detail, bundle)
        }

        setupObservers()

    }

    private fun setupObservers() {
        Log.d(TAG, "setupObservers()")

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { pokemonList ->
            Log.d(TAG, "pokemonList received: $pokemonList")
            pokemonListAdapter.differ.submitList(pokemonList)
        })

        viewModel.loadError.observe(viewLifecycleOwner, Observer { pokemonListError ->
            Log.d(TAG, "pokemonListError received: $pokemonListError")
            if (pokemonListError.isNotEmpty() && pokemonListError.isNotBlank())
                binding?.itemListContainer?.let {
                    Snackbar.make(it, "Error: $pokemonListError", Snackbar.LENGTH_LONG).show()
                }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun showLoading() {
        Log.d(TAG, "showLoading()")
        binding.pbLoading?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        Log.d(TAG, "hideLoading()")
        binding?.pbLoading?.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        Log.d(TAG, "setupRecyclerView()")

        pokemonListAdapter = PokemonListAdapter()
        binding.rvPokemonList?.apply {
            adapter = pokemonListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView()")

        _binding = null
    }
}