package maxwayapi.service

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.OrderDto
import maxwayapi.dto.OrderItemDto
import maxwayapi.model.Delivery
import maxwayapi.model.Order
import maxwayapi.model.ProductItem
import maxwayapi.model.User
import maxwayapi.model.enums.OrderType
import maxwayapi.repository.*
import maxwayapi.service.functionality.Creatable
import maxwayapi.service.functionality.InstanceReturnable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired private val repository: OrderRepository,
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val deliveryRepository: DeliveryRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val productItemRepository: ProductItemRepository
) : InstanceReturnable<Order>, Creatable<OrderDto> {
    override fun getInstanceWithId(id: Long): Order = repository.findById(id).orElse(null)

    override fun getAllInstances(): List<Order> = repository.findAllById((SecurityContextHolder.getContext().authentication.principal as User).id, Sort.by(Sort.Direction.DESC, "id"))

    override fun register(dto: OrderDto): SuperResponse =
        when (dto.orderType) {
            OrderType.SIMPLE -> {
                val createProducts = createProducts(dto.allOrderValue)
                SuperResponse.CREATED_SUCCESSFULLY.setData(
                    repository.save(
                        Order(
                            createProducts,
                            createProducts.stream().map { productItem ->
                                (productItem.product?.price ?: 0.0) * productItem.quantity
                            }.reduce { a, b -> a + b }.get(),
                            comment = dto.comment
                        )
                    )
                )
            }
            else -> {
                val createProducts = createProducts(dto.allOrderValue)
                SuperResponse.CREATED_SUCCESSFULLY.setData(
                    deliveryRepository.save(
                        Delivery(
                            Order(
                                createProducts,
                                createProducts.stream().map { productItem ->
                                    (productItem.product?.price ?: 0.0) * productItem.quantity
                                }.reduce { a, b -> a + b }.get(),
                                comment = dto.comment,
                                OrderType.DELIVERY
                            ),
                            dto.address
                        )
                    )
                )
            }
        }


    private fun createProducts(items: HashSet<OrderItemDto>) = items.filter {
        productRepository.existsById(it.productId)
    }.map {
        ProductItem(productRepository.getReferenceById(it.productId), it.quantity)
    }.toMutableList()

}
