package maxwayapi.service.functionality

import maxwayapi.dao.SuperResponse

interface Creatable<T> {
    fun register(dto: T): SuperResponse
}