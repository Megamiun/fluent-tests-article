package br.com.gabryel.expressive.junit

import br.com.gabryel.expressive.Participant
import br.com.gabryel.expressive.Situation.STRIKE_1
import br.com.gabryel.expressive.correctPointsAndGetScoreboard
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JUnitSamples {
    @Test
    fun `JUnit - given a regular participant, description should have name and updated value rounded to two decimal places`() {
        val participant = Participant("Mario", 1.0 / 3)

        assertEquals(
            participant.description.replace("\\[.+?] ".toRegex(), ""),
            "Mario: 0.33",
            "description"
        )
    }

    @Test
    fun `JUnit - given a regular participant, when he cheats, status should note STRIKE_1 and score should rise by 1`() {
        val participant = Participant("Mario", 10.0)

        val cheater = participant.cheat()

        assertEquals(cheater.situation, STRIKE_1, "status")
        assertEquals(cheater.points, 11, "points")
    }

    @Test
    fun `JUnit - given a list of participants, when ordering list, should return list ordered by points descending`() {
        val participants = listOf(
            Participant("Mario", 10.0),
            Participant("Luigi", 7.0),
            Participant("Peach", 15.0)
        )

        val corrections = mapOf("Mario" to -7.0)
        val ranking = participants.correctPointsAndGetScoreboard(corrections)

        assertEquals(ranking.size, 3, "size")

        assertEquals(ranking[0].name, "Peach", "id[0]")
        assertEquals(ranking[0].points, 15.0, "points[0]")

        assertEquals(ranking[1].name, "Luigi", "id[1]")
        assertEquals(ranking[1].points, 7.0, "points[1]")

        assertEquals(ranking[2].name, "Mario", "id[2]")
        assertEquals(ranking[2].points, 3.0, "points[2]")
    }
}