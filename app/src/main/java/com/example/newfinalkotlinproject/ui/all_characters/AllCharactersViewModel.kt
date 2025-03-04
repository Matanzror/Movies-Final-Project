package com.example.newfinalkotlinproject.ui.all_characters

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.newfinalkotlinproject.data.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class AllCharactersViewModel @Inject constructor(
    characterRepository: CharacterRepository
) :ViewModel() {

    val characters = characterRepository.getCharacters()
}