package com.example.newfinalkotlinproject.ui.single_character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.newfinalkotlinproject.data.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.newfinalkotlinproject.data.repository.CharacterRepository
import com.example.newfinalkotlinproject.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SingleCharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    //fun getCharacter(id:Int) = characterRepository.getCharacter(id)

    private val _id = MutableLiveData<Int>()
    private val _character = _id.switchMap {
        characterRepository.getCharacter(it)
    }
    val character:LiveData<Resource<Character>> = _character

    fun setId(id:Int) {
        _id.value = id
    }

}