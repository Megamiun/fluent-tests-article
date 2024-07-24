package br.com.gabryel.expressive.assertj

import br.com.gabryel.expressive.Participant
import br.com.gabryel.expressive.Situation.STRIKE_1
import br.com.gabryel.expressive.assertj.ParticipantAssert.Companion.assertThat
import br.com.gabryel.expressive.correctPointsAndGetScoreboard
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class AssertJSamples {
    @Test
    fun `AssertJ - given a regular participant, description should have updated name and value rounded to two decimal places`() {
        val participant = Participant("Mario", points = 1.0 / 3)

        assertThat(participant.description)
            .matches("""[.+?] Mario: 0\.33""")

        assertThat(participant)
            .hasRegularDescription("Mario", 0.33)
    }

    @Test
    fun `AssertJ - given a regular participant, when he cheats, status should note STRIKE_1 and score should rise by 1`() {
        val participant = Participant("Mario", 10.0)

        val cheater = participant.cheat()

        assertThat(cheater)
            .isInStrike1()
            .hasPoints(11.0)
    }

    @Test
    fun `AssertJ Soft - given a regular participant, when he cheats, status should note STRIKE_1 and score should rise by 1`() {
        val participant = Participant("Mario", 10.0)

        val cheater = participant.cheat()

        val softly = SoftAssertions()

        softly.assertThat(cheater.situation)
            .`as`("status")
            .isEqualTo(STRIKE_1)

        softly.assertThat(cheater.points)
            .`as`("points")
            .isEqualTo(11.0)

        softly.assertAll()
    }

    @Test
    fun `AssertJ - given a list of participants, when ordering list, should return list ordered by points descending`() {
        val participants = listOf(
            Participant("Mario", 10.0),
            Participant("Luigi", 7.0),
            Participant("Peach", 15.0)
        )

        val corrections = mapOf("Mario" to -7.0)
        val ranking = participants.correctPointsAndGetScoreboard(corrections)

        assertThat(ranking).satisfiesExactly(
            participantWith("Peach", 15.0),
            participantWith("Luigi", 7.0),
            participantWith("Mario", 3.0)
        )
    }

    private fun participantWith(name: String, score: Double): (Participant) -> Unit = { participant ->
        assertThat(participant)
            .hasName(name)
            .hasPoints(score)
    }
}