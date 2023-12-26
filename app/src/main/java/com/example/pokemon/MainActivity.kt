package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.data.PokemonDetailsData
import com.example.pokemon.data.PokemonListData
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.net.Status
import com.example.pokemon.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemClickListener {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var pokemonListData: PokemonListData
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private val sharedPokemonNameList = mutableListOf<String>()
    private var isFilteringData = false
    private lateinit var pokemonDetaiDatalList: ArrayList<PokemonDetailsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        buildRecyclerView()
        buildSearchView()
    }

    private fun buildSearchView() {
        binding.pokemonSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()) {
                    isFilteringData = true
                    filter(newText)
                } else {
                    isFilteringData = false
                    pokemonListAdapter.refreshList(sharedPokemonNameList as ArrayList<String>)
                }
                return false
            }
        })

    }

    private fun filter(text: String?) {
        val filteredList: ArrayList<String> = ArrayList()
        Log.d(TAG, "Nishant -> Pokemon name to search : $text")
        for (name in sharedPokemonNameList) {
            Log.d(TAG, "Nishant -> Pokemon name to match : $name")
            if (text?.lowercase()?.let { name.lowercase().contains(it) }!!) {
                Log.d(TAG, "Nishant -> Pokemon name found is : $name")
                filteredList.add(name)
            }
        }
        Log.d(TAG, "Nishant -> filteredList size : ${filteredList.size}")
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            Log.d(TAG, "Nishant -> Data Found..")
            pokemonListAdapter.refreshList(filteredList)
        }
    }

    private fun buildRecyclerView() {
        //Since flow run asynchronously, start listening on background thread
        lifecycleScope.launch {
            viewModel.pokemonListDataApiState.collect {
                Log.d(TAG, "received pokemon list data")
                when(it.status) {
                    Status.LOADING -> {
                        Toast.makeText(this@MainActivity, "Pokemon list is loading", Toast.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS -> {
                        Log.e(TAG, "success fetching data, message is : ${it.message}")
                        pokemonListData = it.data!!
                        val mutex = Mutex()
                        pokemonListData.let {
                            var pokemonList = pokemonListData.results
                            Log.d(TAG, "pokemon list size is : ${pokemonList.size}" )
                            viewModel.fetchPokemonData(pokemonList)
                            pokemonDetaiDatalList = ArrayList()
                            viewModel.getPokemonDataAPIState.collect { it1 ->
                                Log.d(TAG, "received pokemon data")
                                when(it1.status) {
                                    Status.LOADING -> {
                                        Toast.makeText(this@MainActivity, "Pokemon details data is loading", Toast.LENGTH_SHORT).show()
                                    }
                                    Status.SUCCESS -> {
                                        mutex.withLock {
                                            pokemonDetaiDatalList.add(it1.data!!)
                                            sharedPokemonNameList.add(it1.data?.name!!)
                                            Log.d(TAG, "sharedPokemonNameList.size : ${sharedPokemonNameList.size}")
                                            if(sharedPokemonNameList.size > 0) {
                                                pokemonListAdapter = PokemonListAdapter(this@MainActivity,
                                                    sharedPokemonNameList as ArrayList<String>, pokemonDetaiDatalList, this@MainActivity
                                                )
                                                binding.pokemonRv.layoutManager = LinearLayoutManager(this@MainActivity)
                                                binding.pokemonRv.adapter = pokemonListAdapter
                                            }
                                        }
                                    }
                                    else -> {
                                        Log.e(TAG, "error occurred while fetching pokemon data, message is : ${it1.message}")
                                        Toast.makeText(this@MainActivity, "${it1.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                        }
                    }
                    else -> {
                        Log.e(TAG, "error occurred while fetching pokemon list data, message is : ${it.message}")
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onItemClick(item: PokemonDetailsData) {
        Log.d(TAG, "Nishant -> URL to call -> ${item.pokemon[0].pokemon?.url}")
        Toast.makeText(this@MainActivity, "URL: ${item.pokemon[0].pokemon?.url}", Toast.LENGTH_SHORT).show()

        // start your activity by passing the intent
        startActivity(Intent(this, PokemonInfoActivity::class.java).apply {
            // you can add values(if any) to pass to the next class or avoid using `.apply`
            putExtra("url", item.pokemon[0].pokemon?.url)
            putExtra("pokemonName", item.pokemon[0].pokemon?.name)
        })

    }
}