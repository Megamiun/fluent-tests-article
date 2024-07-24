package br.com.gabryel.expressive

import br.com.gabryel.expressive.Situation.*
import java.math.RoundingMode.HALF_EVEN
import java.util.*

data class Participant(
    val name: String,
    val points: Double = 0.0,
    val situation: Situation = REGULAR,
    val id: UUID = UUID.randomUUID()
) {

    val description = when(situation) {
        REGULAR -> {
            val points = points.toBigDecimal().setScale(2, HALF_EVEN)
            "[$id] $name: $points"
        }
        STRIKE_1 -> {
            val points = points.toBigDecimal().setScale(2, HALF_EVEN)
            "[$id] $name: $points*"
        }
        DISQUALIFIED -> "[$id] $name: DISQUALIFIED"
    }

    fun cheat() = copy(points = points + 10, situation = DISQUALIFIED)

    fun addPoints(pointsToAdd: Double) = copy(points = points + pointsToAdd)

}
