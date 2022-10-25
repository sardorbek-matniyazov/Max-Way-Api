package maxwayapi.service

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.OrderDto
import maxwayapi.dto.OrderItemDto
import maxwayapi.model.Order
import maxwayapi.model.ProductItem
import maxwayapi.repository.*
import maxwayapi.service.functionality.Creatable
import maxwayapi.service.functionality.InstanceReturnable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
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

    override fun getAllInstances(): List<Order> = repository.findAll(Sort.by(Sort.Direction.DESC, "id"))

    override fun create(dto: OrderDto): SuperResponse = SuperResponse.CREATED_SUCCESSFULLY.setData(
        userRepository.findById(dto.userId).map { it ->
            if (it == null) SuperResponse.USER_NOT_FOUND
            else {
                val createProducts = createProducts(dto.items)
                repository.save(
                    Order(
                        user = it,
                        createProducts,
                        createProducts.stream().map { productItem ->
                            productItem.product?.price ?: (0.0 * productItem.quantity)
                        }.reduce { a, b -> a + b }.get(),
                        comment = dto.comment
                    )

                )
            }
        }
    )

    private fun createProducts(items: HashSet<OrderItemDto>) = items.filter {
        productRepository.existsById(it.productId)
    }.map {
        productItemRepository.save(
            ProductItem(productRepository.getReferenceById(it.productId), it.quantity)
        )
    }.toMutableList()

}
