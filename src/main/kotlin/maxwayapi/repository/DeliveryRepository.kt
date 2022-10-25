package maxwayapi.repository

import maxwayapi.model.Delivery
import org.springframework.data.jpa.repository.JpaRepository

interface DeliveryRepository: JpaRepository<Delivery, Long> {

}
