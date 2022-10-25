package maxwayapi.controller

import maxwayapi.dto.ProductDto
import maxwayapi.service.ProductService
import maxwayapi.utils.extensions.handleResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/product"])
class ProductController(@Autowired private val service: ProductService) {
    @GetMapping(value = ["/all"])
    fun getAllProduct() = ResponseEntity.ok(service.getAllInstances())

    @GetMapping(value = ["/{id}"])
    fun getProductWithId(@PathVariable id: Long) = ResponseEntity.ok(service.getInstanceWithId(id))

    @PostMapping(value = ["/create"])
    fun createProduct(@RequestBody @Valid dto: ProductDto) = service.create(dto).handleResponse()

    @PutMapping(value = ["/update/{id}"])
    fun updateProduct(@RequestBody @Valid dto: ProductDto, @PathVariable id: Long) = service.update(id, dto).handleResponse()

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationChecker(e: MethodArgumentNotValidException): HttpEntity<Any> {
        return CategoryController.argumentsNotValidExceptionHandler(e)
    }
}