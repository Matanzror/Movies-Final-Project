package com.example.newfinalkotlinproject.data.repository

import com.example.newfinalkotlinproject.data.loca_db.CharacterDao
import com.example.newfinalkotlinproject.data.remote_db.CharacterRemoteDataSource
import com.example.newfinalkotlinproject.utils.performFetchingAndSaving
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val remoteDataSource : CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {

    fun getCharacters()  = performFetchingAndSaving(
        {localDataSource.getAllCharacters()},
        {remoteDataSource.getCharacters()},
        {localDataSource.insertCharacters(it.results)}
    )

    fun getCharacter(id:Int) = performFetchingAndSaving(
        {localDataSource.getCharacter(id)},
        {remoteDataSource.getCharacter(id)},
        {localDataSource.insertCharacter(it)}
    )
}