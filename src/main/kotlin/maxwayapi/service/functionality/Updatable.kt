package maxwayapi.service.functionality

import maxwayapi.dao.SuperResponse

interface Updatable<I, D> {
    fun update(id: I, dto: D): SuperResponse
}