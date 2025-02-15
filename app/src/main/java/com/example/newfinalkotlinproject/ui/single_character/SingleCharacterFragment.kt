package com.example.newfinalkotlinproject.ui.single_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import com.example.newfinalkotlinproject.databinding.CharacterDetailFragmentBinding
import com.example.newfinalkotlinproject.utils.Loading
import com.example.newfinalkotlinproject.utils.Success
import com.example.newfinalkotlinproject.utils.Error
import com.example.newfinalkotlinproject.utils.autoCleared

import com.example.newfinalkotlinproject.data.models.Character

@AndroidEntryPoint
class SingleCharacterFragment : Fragment() {


    private var binding: CharacterDetailFragmentBinding by autoCleared()

    private val viewModel: SingleCharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       viewModel.character.observe(viewLifecycleOwner) {
           when(it.status) {
               is Loading -> binding.progressBar.isVisible = true
               is Success -> {
                   binding.progressBar.isVisible = false
                    updateCharacter(it.status.data!!)
               }
               is Error -> {
                   binding.progressBar.isVisible = false
                   Toast.makeText(requireContext(),it.status.message,Toast.LENGTH_SHORT).show()
               }
           }
       }

        arguments?.getInt("id")?.let {

            viewModel.setId(it)
        }
    }

    private fun updateCharacter(character: Character) {

        binding.name.text = character.name
        binding.gender.text = character.gender
        binding.species.text = character.species
        binding.status.text = character.status
        Glide.with(requireContext()).load(character.image).circleCrop().into(binding.image)

    }
}