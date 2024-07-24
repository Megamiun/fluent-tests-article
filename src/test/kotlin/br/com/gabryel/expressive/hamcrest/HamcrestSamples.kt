package br.com.gabryel.expressive.hamcrest

import br.com.gabryel.expressive.Participant
import br.com.gabryel.expressive.Situation.STRIKE_1
import br.com.gabryel.expressive.correctPointsAndGetScoreboard
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.beans.HasPropertyWithValue.hasProperty
import org.hamcrest.core.StringRegularExpression.matchesRegex
import org.hobsoft.hamcrest.compose.ComposeMatchers.compose
import org.junit.jupiter.api.Test

class HamcrestSamples {
    @Test
    fun `Hamcrest - given a regular participant, description should have name and updated value rounded to two decimal places`() {
        val participant = Participant("Mario", points = 1.0 / 3)

//        assertThat("description", participant.description,
//            matchesRegex("""\[.+?] Mario: 0\.33"""))

        assertThat(participant,
            hasRegularDescription("Mario", 0.33))
    }

    @Test
    fun `Hamcrest - given a regular participant, when he cheats, status should note STRIKE_1 and score should rise by 1`() {
        val participant = Participant("Mario", 10.0)

        val cheater = participant.cheat()

        assertThat(cheater, compose(
            isInStrike1(),
            hasPoints(11.0)
        ))
    }
    @Test
    fun `Hamcrest - given a list of participants, when ordering list, should return list ordered by points descending`() {
        val participants = listOf(
            Participant("Mario", 10.0),
            Participant("Luigi", 7.0),
            Participant("Peach", 15.0)
        )

        val corrections = mapOf("Mario" to -7.0)
        val ranking = participants.correctPointsAndGetScoreboard(corrections)

        assertThat(ranking, contains(
            participantWith("Peach", 15.0),
            participantWith("Luigi", 7.0),
            participantWith("Mario", 3.0)
        ))
    }

    private fun participantWith(name: String, score: Double) = compose(
        hasName(name),
        hasPoints(score)
    )

//    private fun hasName(name: String): Matcher<Participant> =
//        hasFeature("name", { it.name }, equalTo(name))
//
//    private fun hasPoints(points: Double): Matcher<Participant> =
//        hasFeature("points", { it.points }, equalTo(points))
//
//    private fun isInStrike1(): Matcher<Participant> =
//        hasFeature("status", { it.situation }, equalTo(Situation.STRIKE_1))
//
//    private fun hasRegularDescription(name: String, points: Double): Matcher<Participant> =
//        hasFeature("description", { it.description }, matchesRegex("""\[.+?] $name: $points"""))

    private fun hasName(name: String): Matcher<Participant> =
        hasProperty("name", equalTo(name))

    private fun hasPoints(points: Double): Matcher<Participant> =
        hasProperty("points", equalTo(points))

    private fun isInStrike1(): Matcher<Participant> =
        hasProperty("situation", equalTo(STRIKE_1))

    private fun hasRegularDescription(name: String, points: Double): Matcher<Participant> =
        hasProperty("description", matchesRegex("""\[.+?] $name: $points"""))
}
