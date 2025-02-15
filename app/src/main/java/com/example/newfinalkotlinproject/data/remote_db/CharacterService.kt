package com.example.newfinalkotlinproject.data.remote_db

import com.example.newfinalkotlinproject.data.models.AllCharacters
import com.example.newfinalkotlinproject.data.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters() : Response<AllCharacters>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id")id :Int) : Response<Character>
}