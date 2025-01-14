package com.daromi.playbook.tracker.domain

interface ShowStore {
    fun save(show: Show)

    fun search(id: ShowId): Show?

    fun exists(id: ShowId): Boolean
}
