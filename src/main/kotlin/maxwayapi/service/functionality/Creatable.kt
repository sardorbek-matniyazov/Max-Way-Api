package maxwayapi.service.functionality

import maxwayapi.dao.SuperResponse

interface Creatable<T> {
    fun create(dto: T): SuperResponse
}