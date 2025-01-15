package com.daromi.playbook.core.shows.domain

import java.util.SortedMap

class Show private constructor(
    private val id: ShowId,
    private val name: ShowName,
    private val seasons: SortedMap<SeasonNumber, Season> = sortedMapOf(),
) {
    companion object {
        @JvmStatic
        fun create(
            rawId: String,
            rawName: String,
        ): Show? {
            val id = ShowId.from(rawId) ?: return null
            val name = ShowName.from(rawName) ?: return null

            return Show(id, name)
        }
    }
}

class Season private constructor(
    private val number: SeasonNumber,
)

@JvmInline
value class ShowId(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun from(value: String): ShowId? {
            if (value.isBlank()) return null

            return ShowId(value)
        }
    }
}

@JvmInline
private value class ShowName(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun from(value: String): ShowName? {
            if (value.isBlank()) return null

            return ShowName(value)
        }
    }
}

@JvmInline
private value class SeasonNumber(
    val value: Int,
) : Comparable<SeasonNumber> {
    companion object {
        @JvmStatic
        fun from(value: Int): SeasonNumber? {
            if (value <= 0) return null

            return SeasonNumber(value)
        }
    }

    override fun compareTo(other: SeasonNumber): Int = this.value - other.value
}
