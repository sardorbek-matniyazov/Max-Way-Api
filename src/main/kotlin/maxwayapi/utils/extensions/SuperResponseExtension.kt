package maxwayapi.utils.extensions

import maxwayapi.dao.SuperResponse
import org.springframework.http.ResponseEntity


fun SuperResponse.handleResponse(): ResponseEntity<SuperResponse> {
    return ResponseEntity.status(this.status).body(this)
}