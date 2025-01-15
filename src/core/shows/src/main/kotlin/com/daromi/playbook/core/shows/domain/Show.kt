package com.daromi.playbook.core.shows.domain

import java.time.LocalDate
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

    fun addSeason(rawNumber: Int): Boolean {
        val number = SeasonNumber.from(rawNumber) ?: return false

        // Ensure the season does not already exist
        if (this.seasons.containsKey(number)) {
            return false
        }

        // Ensure the first season is added first
        if (this.seasons.isEmpty() && number != SeasonNumber.FIRST) {
            return false
        }

        // Ensure seasons are added sequentially
        if (this.seasons.isNotEmpty()) {
            val last = this.seasons.lastKey()

            if (last.next() != number) {
                return false
            }
        }

        val season = Season.create(number)

        this.seasons[number] = season

        return true
    }
}

private class Season private constructor(
    private val number: SeasonNumber,
    private val episodes: SortedMap<EpisodeNumber, Episode> = sortedMapOf(),
) {
    companion object {
        @JvmStatic
        fun create(number: SeasonNumber): Season = Season(number)
    }
}

private data class Episode(
    val number: EpisodeNumber,
    val name: EpisodeName,
    val releaseDate: EpisodeReleaseDate,
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
        val FIRST: SeasonNumber = SeasonNumber(1)

        @JvmStatic
        fun from(value: Int): SeasonNumber? {
            if (value <= 0) return null

            return SeasonNumber(value)
        }
    }

    fun next(): SeasonNumber = SeasonNumber(this.value + 1)

    override fun compareTo(other: SeasonNumber): Int = this.value - other.value
}

@JvmInline
private value class EpisodeNumber(
    val value: Int,
) : Comparable<EpisodeNumber> {
    companion object {
        @JvmStatic
        fun from(value: Int): EpisodeNumber? {
            if (value <= 0) return null

            return EpisodeNumber(value)
        }
    }

    override fun compareTo(other: EpisodeNumber): Int = this.value - other.value
}

@JvmInline
private value class EpisodeName(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun from(value: String): EpisodeName? {
            if (value.isBlank()) return null

            return EpisodeName(value)
        }
    }
}

@JvmInline
private value class EpisodeReleaseDate(
    val value: LocalDate,
)
