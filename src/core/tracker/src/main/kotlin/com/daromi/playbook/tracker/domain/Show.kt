package com.daromi.playbook.tracker.domain

class Show private constructor(
    private val id: ShowId,
    private val name: ShowName,
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
