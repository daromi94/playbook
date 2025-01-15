package com.daromi.playbook.core.shows.domain

interface ShowStore {
    fun save(show: Show)

    fun search(id: ShowId): Show?

    fun exists(id: ShowId): Boolean
}
