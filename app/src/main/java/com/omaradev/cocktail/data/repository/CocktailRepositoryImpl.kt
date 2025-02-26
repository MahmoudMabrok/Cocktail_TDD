package com.omaradev.cocktail.data.repository

import android.content.SharedPreferences
import com.omaradev.cocktail.domain.repository.CocktailRepository

class CocktailRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    CocktailRepository {
    private val highScoreKey = "HIGH_SCORE"
    override fun saveScore(score: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(highScoreKey, score)
        editor.apply()
    }

    override fun getScore(): Int {
        val score = sharedPreferences.getInt(highScoreKey, 0)
        return score
    }
}