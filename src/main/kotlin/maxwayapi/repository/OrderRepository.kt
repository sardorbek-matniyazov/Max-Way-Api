package maxwayapi.repository

import maxwayapi.model.Order
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findAllById(id: Long, sort: Sort): MutableList<Order>

}
