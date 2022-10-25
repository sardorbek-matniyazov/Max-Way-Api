package maxwayapi.service.functionality

import maxwayapi.dao.SuperResponse

interface Deletable<I> {
    fun delete(id: I): SuperResponse
}