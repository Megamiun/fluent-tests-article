package br.com.gabryel.expressive

import br.com.gabryel.expressive.Situation.DISQUALIFIED

fun List<Participant>.correctPointsAndGetScoreboard(corrections: Map<String, Double> = emptyMap()) =
    filter { it.situation != DISQUALIFIED }
        .map { it.addPoints(corrections[it.name] ?: 0.0) }
        .sortedByDescending { it.points }