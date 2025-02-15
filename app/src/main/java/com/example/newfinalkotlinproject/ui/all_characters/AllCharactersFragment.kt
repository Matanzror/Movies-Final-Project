package com.example.newfinalkotlinproject.ui.all_characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.example.newfinalkotlinproject.R
import com.example.newfinalkotlinproject.databinding.CharactersFragmentBinding
import com.example.newfinalkotlinproject.utils.Loading
import com.example.newfinalkotlinproject.utils.Success
import com.example.newfinalkotlinproject.utils.Error
import com.example.newfinalkotlinproject.utils.autoCleared

@AndroidEntryPoint
class AllCharactersFragment : Fragment(), CharactersAdapter.CharacterItemListener {

    private var binding : CharactersFragmentBinding by autoCleared()

    private val viewModel: AllCharactersViewModel by viewModels()

    private  lateinit var  adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharactersFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter

        viewModel.characters.observe(viewLifecycleOwner) {
            when(it.status) {
                is Loading -> binding.progressBar.isVisible = true
                is Success -> {
                    if(!it.status.data.isNullOrEmpty()) {
                        binding.progressBar.isVisible = false
                        adapter.setCharacters(ArrayList(it.status.data))
                    }
                }
                is Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(),it.status.message,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCharacterClick(characterId: Int) {
       findNavController().navigate(R.id.action_allCharactersFragment_to_singleCharacterFragment,
           bundleOf("id" to characterId))
    }
}