package com.garciafrancisco.pokedex.ui.pokemonlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.garciafrancisco.pokedex.R
import com.garciafrancisco.pokedex.databinding.FragmentPokemonListBinding
import com.garciafrancisco.pokedex.repository.PokedexRepository
import com.garciafrancisco.pokedex.ui.pokemondetail.PokemonDetailFragment.Companion.ARG_POKEMON_ID
import com.garciafrancisco.pokedex.util.Constants.PAGE_SIZE
import com.google.android.material.snackbar.Snackbar

private const val TAG = "PokemonListFragment"

class PokemonListFragment : Fragment() {


    lateinit var viewModel: PokemonListViewModel
    lateinit var pokemonListAdapter: PokemonListAdapter

    private var _binding: FragmentPokemonListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView()")
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")

        val viewModelProviderFactory = PokemonListViewModelProviderFactory(PokedexRepository())
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[PokemonListViewModel::class.java]

        setupRecyclerView()

        pokemonListAdapter.setOnItemClickListener { pokeApiId ->
            Log.d(TAG, "OnItemClickListener(): $pokeApiId")
            val bundle = Bundle().apply {
                putSerializable(ARG_POKEMON_ID, pokeApiId.toString())
            }
            findNavController().navigate(R.id.list_to_detail, bundle)
        }
        setupObservers()

    }

    private fun setupObservers() {
        Log.d(TAG, "setupObservers()")
        viewModel.pokemonList.observe(viewLifecycleOwner) { pokemonList ->
            Log.d(TAG, "pokemonList received: $pokemonList")
            pokemonListAdapter.differ.submitList(pokemonList)
            // we add +2 because:
            // integer divisions are always rounded off
            // the last page of the response will always be empty, so we need to +1
            val totalPokemonPages = viewModel.totalResults() / PAGE_SIZE + 2
            isLastPage = viewModel.currentPokemonPage() == totalPokemonPages
        }

        viewModel.loadError.observe(viewLifecycleOwner) { pokemonListError ->
            Log.d(TAG, "pokemonListError received: $pokemonListError")
            if (pokemonListError.isNotEmpty() && pokemonListError.isNotBlank())
                binding.itemListContainer.let {
                    Snackbar.make(it, "Error: $pokemonListError", Snackbar.LENGTH_LONG).show()
                }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    private fun showLoading() {
        Log.d(TAG, "showLoading()")
        binding.pbLoading?.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideLoading() {
        Log.d(TAG, "hideLoading()")
        binding.pbLoading?.visibility = View.GONE
        isLoading = false
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            Log.d(TAG, "onScrolled()")

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadinbAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= PAGE_SIZE

            val shouldPaginate =
                isNotLoadinbAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            Log.d(TAG, "shouldPaginate = $shouldPaginate")

            if (shouldPaginate) {
                viewModel.loadPokemonPaginated()
                isScrolling = false
            }
        }
    }

    private fun setupRecyclerView() {
        Log.d(TAG, "setupRecyclerView()")

        pokemonListAdapter = PokemonListAdapter()
        binding.rvPokemonList?.apply {
            adapter = pokemonListAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@PokemonListFragment.scrollListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView()")
        _binding = null
    }
}