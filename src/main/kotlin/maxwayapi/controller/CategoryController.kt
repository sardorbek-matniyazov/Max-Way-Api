package maxwayapi.controller

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.CategoryDto
import maxwayapi.service.CategoryService
import maxwayapi.utils.extensions.handleResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.function.Consumer
import javax.validation.Valid

@RestController
@RequestMapping(value = ["api/v1/category"])
class CategoryController(@Autowired private val service: CategoryService) {

    @GetMapping(value = ["/all"])
    fun getAllCategories() = ResponseEntity.ok(service.getAllInstances())

    @GetMapping(value = ["/{id}"])
    fun getCategoryWithId(@PathVariable id: Long) = ResponseEntity.ok(service.getInstanceWithId(id))

    @PostMapping(value = ["/create"])
    fun createCategory(@RequestBody @Valid dto: CategoryDto) = service.create(dto).handleResponse()

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationChecker(e: MethodArgumentNotValidException): HttpEntity<Any> {
        return argumentsNotValidExceptionHandler(e)
    }

    companion object {
        fun argumentsNotValidExceptionHandler(e: MethodArgumentNotValidException): HttpEntity<Any> {
            val message = StringBuilder()
            e.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
                val errorMessage = error.defaultMessage
                message.append(errorMessage).append(", ")
            })
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(SuperResponse(message.substring(0, message.length - 2)))
        }
    }
}