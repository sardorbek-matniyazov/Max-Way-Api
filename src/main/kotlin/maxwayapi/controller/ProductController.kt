package maxwayapi.controller

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.ProductDto
import maxwayapi.service.ProductService
import maxwayapi.utils.extensions.handleResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/product"])
@PreAuthorize(value = "hasAnyAuthority('USER')")
class ProductController(@Autowired private val service: ProductService) {
    @GetMapping(value = ["/all"])
    fun getAllOrders() = SuperResponse.ALL_DATA.setData(service.getAllInstances()).handleResponse()

    @GetMapping(value = ["/{id}"])
    fun getOrderWithId(@PathVariable id: Long) =
        SuperResponse.DATA.setData(service.getInstanceWithId(id)).handleResponse()

    @PostMapping(value = ["/create"])
    fun createProduct(@RequestBody @Valid dto: ProductDto) = service.register(dto).handleResponse()

    @PutMapping(value = ["/update/{id}"])
    fun updateProduct(@RequestBody @Valid dto: ProductDto, @PathVariable id: Long) =
        service.update(id, dto).handleResponse()

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationChecker(e: MethodArgumentNotValidException): HttpEntity<Any> {
        return CategoryController.argumentsNotValidExceptionHandler(e)
    }
}