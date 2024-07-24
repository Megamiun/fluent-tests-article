package br.com.gabryel.expressive.assertj

import br.com.gabryel.expressive.Participant
import br.com.gabryel.expressive.Situation.STRIKE_1
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset

class ParticipantAssert(participant: Participant): AbstractObjectAssert<ParticipantAssert, Participant>(participant, ParticipantAssert::class.java) {

    companion object {
        fun assertThat(participant: Participant) = ParticipantAssert(participant)
    }

    fun hasName(name: String): ParticipantAssert {
        assertThat(actual.name)
            .`as`("name")
            .isEqualTo(name)
        return this
    }

    fun hasPoints(points: Double): ParticipantAssert {
        assertThat(actual.points)
            .`as`("points")
            .isCloseTo(points, offset(0.001))
        return this
    }

    fun isInStrike1(): ParticipantAssert {
        assertThat(actual.situation)
            .`as`("status")
            .isEqualTo(STRIKE_1)
        return this
    }

    fun hasRegularDescription(name: String, points: Double): ParticipantAssert {
        assertThat(actual.description)
            .`as`("description")
            .matches("""\[.*?] $name: $points""")
        return this
    }
}
